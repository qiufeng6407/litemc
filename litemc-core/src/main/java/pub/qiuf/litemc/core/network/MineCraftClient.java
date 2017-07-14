package pub.qiuf.litemc.core.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.common.bean.ClientState;
import pub.qiuf.litemc.common.bean.ServerInfo;
import pub.qiuf.litemc.common.facade.Command;
import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.core.manager.ChunkManager;
import pub.qiuf.litemc.core.manager.PlayerManager;
import pub.qiuf.litemc.core.manager.PositionManager;
import pub.qiuf.litemc.core.manager.WindowManager;
import pub.qiuf.litemc.core.network.codec.BasePacketDecoder;
import pub.qiuf.litemc.core.network.codec.ClientEventEncoder;
import pub.qiuf.litemc.core.network.codec.ServerEventDecoder;
import pub.qiuf.litemc.protocol.client.handshake.HandshakeEvent;
import pub.qiuf.litemc.protocol.client.login.LoginStartEvent;
import pub.qiuf.litemc.protocol.server.login.DisconnectEvent;
import pub.qiuf.litemc.protocol.server.login.LoginSuccessEvent;
import pub.qiuf.litemc.protocol.server.login.SetCompressionEvent;

import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class MineCraftClient extends Thread {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final String playerName;
    protected final ServerInfo serverInfo;
    protected int threshold = -1;
    protected long clientTick = 1;
    protected short actionNumber = 1;
    protected ClientState clientState = ClientState.HANDSHAKE;
    protected static final long CLIENT_FPS_TIME = 50 * 1000000;

    protected volatile boolean running = false;
    protected ChannelHandlerContext channelHandlerContext;

    protected PlayerManager playerManager;
    protected PositionManager positionManager;
    protected ChunkManager chunkManager;
    protected WindowManager windowManager;

    protected Command command;

    protected final EventBus serverEventBus = new EventBus("ServerEventBus");

    public MineCraftClient(String playerName, ServerInfo serverInfo) {
        this.playerName = playerName;
        this.serverInfo = serverInfo;
    }

    public void registerServerEventObserver(Object object) {
        Preconditions.checkNotNull(object);
        serverEventBus.register(object);
    }

    public void unregisterServerEventObserver(Object object) {
        Preconditions.checkNotNull(object);
        serverEventBus.unregister(object);
    }

    public void handleServerEvent(ServerEvent serverEvent) {
        Preconditions.checkNotNull(serverEvent);
        serverEventBus.post(serverEvent);
    }

    public synchronized void postClientEvent(ClientEvent clientEvent) throws Exception {
        Preconditions.checkNotNull(clientEvent);
        channelHandlerContext.channel().writeAndFlush(clientEvent);
    }

    public void playerDisconnect(String message) {
        running = false;
        channelHandlerContext.disconnect();
        logger.info("disconnect: {}.", message);
    }

    @Override
    public void run() {
        Thread.currentThread().setName(playerName);
        logger.info("litemc starting...");
        final EventLoopGroup workerGroup = new NioEventLoopGroup();
        final Bootstrap b = new Bootstrap();
        final MineCraftClient _this = this;
        try {
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new BasePacketDecoder(_this));
                    ch.pipeline().addLast(new ServerEventDecoder(_this));
                    ch.pipeline().addLast(new ClientEventEncoder(_this));
                }
            });

            ChannelFuture channelFuture = b.connect(serverInfo.getServerAddress(), serverInfo.getServerPort());
            logger.info("connecting server...");

            channelFuture.sync();
            logger.info("connect server success.");

            playerManager = new PlayerManager(this);
            positionManager = new PositionManager(this);
            chunkManager = new ChunkManager(this);
            windowManager = new WindowManager(this);
            registerServerEventObserver(this);
            registerServerEventObserver(playerManager);
            registerServerEventObserver(chunkManager);
            registerServerEventObserver(positionManager);
            registerServerEventObserver(windowManager);

            clientState = ClientState.HANDSHAKE;
            channelHandlerContext.channel().writeAndFlush(
                    new HandshakeEvent(serverInfo.getProtocolVersion(), serverInfo.getServerAddress(), serverInfo.getServerPort(), 2));
            logger.info("handshaking...");

            clientState = ClientState.LOGIN;
            channelHandlerContext.channel().writeAndFlush(new LoginStartEvent(playerName));
            logger.info("logining...");

            running = true;
            while (running) {
                long loopStartTime = System.nanoTime();
                try {
                    if (playerManager.isPlayerRespawning()) {
                        continue;
                    }
                    positionManager.clientTickMovement(clientTick);
                    if (playerManager.isPlayerDied()) {
                        continue;
                    }
                    if (command != null) {
                        command.execute(clientTick);
                    }
                    if (!positionManager.isOnGround()) {
                        double feetY = ((long) (Math.round(positionManager.getFeetY()) * 100)) / 100;
                        positionManager.setPlayerPosition(positionManager.getX(), feetY, positionManager.getZ(), true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    clientTick++;
                    try {
                        if (System.nanoTime() - loopStartTime > CLIENT_FPS_TIME) {
                            continue;
                        }
                        Thread.sleep((CLIENT_FPS_TIME - (System.nanoTime() - loopStartTime)) / 1000000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            channelFuture.channel().closeFuture().sync();

            logger.info("litemc stoped...");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    // =======================================================================
    @Subscribe
    public void onDisconnect(DisconnectEvent serverEvent) throws Exception {
        playerDisconnect("[login] " + serverEvent.getReason());
    }

    @Subscribe
    public void onLoginSuccess(LoginSuccessEvent serverEvent) throws Exception {
        this.clientState = ClientState.PLAY;
        logger.info("login success!");
        unregisterServerEventObserver(this);
    }

    @Subscribe
    public void onSetCompression(SetCompressionEvent serverEvent) throws Exception {
        this.threshold = serverEvent.getThreshold();
        logger.info("set compression {}.", serverEvent.getThreshold());
    }

    public boolean isRunning() {
        return running;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getThreshold() {
        return threshold;
    }

    public ClientState getClientState() {
        return clientState;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public PositionManager getPositionManager() {
        return positionManager;
    }

    public ChunkManager getChunkManager() {
        return chunkManager;
    }

    public WindowManager getWindowManager() {
        return windowManager;
    }

    public synchronized short getActionNumber() {
        short currentActionNumber = actionNumber;
        actionNumber++;
        return currentActionNumber;
    }

    public long getClientTick() {
        return clientTick;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public ChannelHandlerContext getChannelHandlerContext() {
        return channelHandlerContext;
    }

    public void setChannelHandlerContext(ChannelHandlerContext channelHandlerContext) {
        this.channelHandlerContext = channelHandlerContext;
    }

}
