package pub.qiuf.litemc.core.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.core.network.MineCraftClient;

public class BasePacketDecoder extends ReplayingDecoder<Void> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final MineCraftClient mineCraftClient;

    public BasePacketDecoder(MineCraftClient mineCraftClient) {
        this.mineCraftClient = mineCraftClient;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Thread.currentThread().setName(mineCraftClient.getPlayerName());
        mineCraftClient.setChannelHandlerContext(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        mineCraftClient.playerDisconnect("Channel Inactive");
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int result = 0;

        in.markReaderIndex();

        boolean lengthFiledDecodeSuccess = false;
        for (int i = 0; i < in.readableBytes(); i++) {
            if (i > 4) {
                throw new RuntimeException("VarInt is too big");
            }
            byte currByte = in.readByte();
            int value = (currByte & 0b01111111);
            result |= (value << (7 * i));
            if ((currByte & 0b10000000) == 0) {
                lengthFiledDecodeSuccess = true;
                break;
            }
        }
        if (!lengthFiledDecodeSuccess || in.readableBytes() < result) {
            in.resetReaderIndex();
            return;
        }
        byte[] packet = new byte[result];
        in.readBytes(packet);
        out.add(packet);
        packet = null;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
