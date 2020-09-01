package pub.qiuf.litemc.core.context;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.qiuf.litemc.common.bean.ClientState;
import pub.qiuf.litemc.common.bean.datatype.Packet;
import pub.qiuf.litemc.core.manager.*;
import pub.qiuf.litemc.core.network.codec.ClientPacketDecoder;
import pub.qiuf.litemc.core.network.codec.ServerEventEncoder;
import pub.qiuf.litemc.core.network.codec.ClientEventDecoder;
import pub.qiuf.litemc.protocol.handshaking.server.Handshaking_Server_Handshake;
import pub.qiuf.litemc.protocol.login.client.Login_Client_Disconnect;
import pub.qiuf.litemc.protocol.login.client.Login_Client_LoginSuccess;
import pub.qiuf.litemc.protocol.login.server.Login_Server_LoginStart;
import pub.qiuf.litemc.protocol.play.client.Play_Client_Disconnect;
import pub.qiuf.litemc.protocol.play.client.Play_Client_KeepAlive;
import pub.qiuf.litemc.protocol.play.server.Play_Server_KeepAlive;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class GameContext {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private String serverAddress;
    private int serverPort;
    private String playerName;
    private int protocolVersion;
    @Setter
    private int threshold = -1;

    private PlayerManager playerManager;
    private PositionManager positionManager;
    private ChunkManager chunkManager;
    private WindowManager windowManager;
    private ChatManager chatManager;

    @Setter
    private ChannelHandlerContext channelHandlerContext;
    private AtomicLong clientTick = new AtomicLong(1);
    @Setter
    private ClientState clientState = ClientState.HANDSHAKE;
    private ExecutorService executor = Executors.newFixedThreadPool(20);
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    private ClientTickCalculator clientTickCalculator = new ClientTickCalculator();
    private ServerEventConsumer serverEventConsumer = new ServerEventConsumer();
    private ClientEventPoster clientEventPoster = new ClientEventPoster();
    private BlockingDeque<Packet> serverEventQueue = new LinkedBlockingDeque<>();
    private BlockingDeque<Packet> clientEventQueue = new LinkedBlockingDeque<>();
    private EventBus clientEventBus = new EventBus("clientEvent");
    private EventBus clientTickEventBus = new EventBus("clientTick");

    private ReentrantLock lock = new ReentrantLock();
    private Condition clientTickCondition = lock.newCondition();
    private Condition serverEventConsumerCondition = lock.newCondition();
    private Condition clientEventPosterCondition = lock.newCondition();

    public GameContext(String serverAddress, int serverPort, String playerName, int protocolVersion) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.playerName = playerName;
        this.protocolVersion = protocolVersion;
    }

    @Subscribe
    public void on_Login_Client_Disconnect(Login_Client_Disconnect event) {
        logger.debug("[Login Disconnect] " + event.getReason());
        this.clientState = ClientState.DISCONNECT;
    }

    @Subscribe
    public void on_Play_Client_Disconnect(Play_Client_Disconnect event) {
        logger.debug("[Play Disconnect] " + event.getReason());
        this.clientState = ClientState.DISCONNECT;
    }

    @Subscribe
    public void on_Login_Client_LoginSuccess(Login_Client_LoginSuccess event) {
        logger.info("[Login Success] uuid={}({})", event.getUuid(), event.getUsername());
        playerManager.setUuid(event.getUuid());
        this.clientState = ClientState.PLAY;
//        serverEventQueue.offer(Play_Server_ClientSettings.builder().locale("zh_CN").viewDistance((byte) 2).chatMode(0).chatColors(true).displayedSkinParts(1).mainHand(2).build());
    }

    @Subscribe
    public void on_Play_Client_KeepAlive(Play_Client_KeepAlive event) {
        logger.info("[Keep Alive] {}", event.getKeepAliveId());
        serverEventQueue.offer(Play_Server_KeepAlive.builder().keepAliveId(event.getKeepAliveId()).build());
    }

    public void start() {
        Thread.currentThread().setName(this.playerName);
        logger.info("context starting...");

        playerManager = new PlayerManager(this);
        positionManager = new PositionManager(this);
        chunkManager = new ChunkManager(this);
        windowManager = new WindowManager(this);
        chatManager = new ChatManager(this);

        // 客户端事件总线
        clientEventBus.register(playerManager);
        clientEventBus.register(positionManager);
        clientEventBus.register(chunkManager);
        clientEventBus.register(windowManager);
        clientEventBus.register(chatManager);
        clientEventBus.register(this);

        // 客户端Tick总线
        clientTickEventBus.register(positionManager);

        executor.execute(clientTickCalculator);
        executor.execute(clientEventPoster);
        executor.execute(serverEventConsumer);
        executor.execute(positionManager);

        try {
            ClientEventDecoder clientEventDecoder = new ClientEventDecoder(this);
            ClientPacketDecoder clientPacketDecoder = new ClientPacketDecoder(this);
            ServerEventEncoder serverEventEncoder = new ServerEventEncoder(this);

            final Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(clientPacketDecoder);
                    ch.pipeline().addLast(clientEventDecoder);
                    ch.pipeline().addLast(serverEventEncoder);
                }
            });

            logger.info("connect server...");
            ChannelFuture channelFuture = b.connect(serverAddress, serverPort).sync();
            logger.info("connect success!");

            logger.info("handshaking...");
            channelHandlerContext.channel().writeAndFlush(Handshaking_Server_Handshake.builder().protocolVersion(protocolVersion)
                    .serverAddress(serverAddress).serverPort(serverPort).nextState(2).build());
            setClientState(ClientState.LOGIN);
            logger.info("login start...");
            channelHandlerContext.channel().writeAndFlush(Login_Server_LoginStart.builder().name(playerName).build());

            channelFuture.channel().closeFuture().sync();
            this.clientState = ClientState.DISCONNECT;
            logger.info("context stoped!");
        } catch (Exception e) {
            logger.error("Unexpected Exception", e);
        } finally {
            workerGroup.shutdownGracefully();
            executor.shutdownNow();
        }
    }

    private class ServerEventConsumer implements Runnable {
        @Override
        public void run() {
            try {
                while (GameContext.this.clientState != ClientState.DISCONNECT) {
                    Packet message = serverEventQueue.take();
                    logger.info("send {}", message.getClass().getSimpleName());
                    channelHandlerContext.channel().writeAndFlush(message);
                }
            } catch (InterruptedException e) {
                logger.error("ServerEventConsumer Interrupted");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class ClientEventPoster implements Runnable {
        @Override
        public void run() {
            try {
                while (GameContext.this.clientState != ClientState.DISCONNECT) {
                    clientEventBus.post(clientEventQueue.take());
                }
            } catch (InterruptedException e) {
                logger.error("ClientEventPoster Interrupted");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class ClientTickCalculator implements Runnable {
        private static final long CLIENT_FPS_TIME = 50 * 1000000;
        @Override
        public void run() {
            while (GameContext.this.getClientState() != ClientState.DISCONNECT) {
                long loopStartTime = System.nanoTime();
                if (GameContext.this.getClientState() == ClientState.PLAY && !GameContext.this.getPlayerManager().isDied()) {
                    GameContext.this.getClientTickEventBus().post(clientTick.incrementAndGet());
                }
                try {
                    long deltaNanoTime = System.nanoTime() - loopStartTime;
                    if (deltaNanoTime >= CLIENT_FPS_TIME) {
                        continue;
                    }
                    long ms = (CLIENT_FPS_TIME - deltaNanoTime) / 1000000;
                    if (ms > 0) {
                        Thread.sleep(ms);
                    }
                } catch (InterruptedException e) {
                    logger.error("ClientTickCalculator Interrupted");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
