package pub.qiuf.litemc.core.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.common.bean.ClientState;
import pub.qiuf.litemc.core.context.GameContext;
import pub.qiuf.litemc.core.network.MineCraftClient;
import pub.qiuf.litemc.protocol.handshaking.server.Handshaking_Server_Handshake;
import pub.qiuf.litemc.protocol.login.server.Login_Server_LoginStart;

public class ClientPacketDecoder extends ReplayingDecoder<Void> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final GameContext gameCtx;

    public ClientPacketDecoder(GameContext gameCtx) {
        this.gameCtx = gameCtx;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Thread.currentThread().setName(gameCtx.getPlayerName());
        gameCtx.setChannelHandlerContext(ctx);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        int packetLength = 0, i = 0;
        while (true) {
            byte currByte = in.readByte();
            int value = (currByte & 0b01111111);
            packetLength |= (value << (7 * i ++));
            if ((currByte & 0b10000000) == 0) {
                break;
            }
        }

        byte[] packet = new byte[packetLength];
        in.readBytes(packet);
        out.add(packet);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        logger.error("exceptionCaught", cause);
        logger.error(cause.getMessage());
        ctx.close();
    }
}
