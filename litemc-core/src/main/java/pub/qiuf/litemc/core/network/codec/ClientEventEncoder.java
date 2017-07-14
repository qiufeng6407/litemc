package pub.qiuf.litemc.core.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.ByteUtils;
import pub.qiuf.litemc.common.util.VarUtils;
import pub.qiuf.litemc.core.network.MineCraftClient;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ClientEventEncoder extends MessageToByteEncoder<ClientEvent> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final MineCraftClient mineCraftClient;

    public ClientEventEncoder(MineCraftClient mineCraftClient) {
        this.mineCraftClient = mineCraftClient;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ClientEvent clientEvent, ByteBuf out) throws Exception {
        byte[] msg = clientEvent.encode();

        logger.debug(ByteUtils.bytestoHexString(msg));

        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        if (mineCraftClient.getThreshold() < 0) { // 不压缩
            packet.write(VarUtils.encodeVarInt(msg.length));
            packet.write(msg);
        } else { // 压缩
            if (mineCraftClient.getThreshold() >= msg.length) { // 未超过最大长度
                packet.write(VarUtils.encodeVarInt(msg.length + 1));
                packet.write(VarUtils.encodeVarInt(0));
                packet.write(msg);
            }
        }
        out.writeBytes(packet.toByteArray());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
