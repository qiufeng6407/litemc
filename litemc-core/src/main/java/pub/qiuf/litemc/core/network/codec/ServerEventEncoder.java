package pub.qiuf.litemc.core.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.common.bean.datatype.Packet;
import pub.qiuf.litemc.common.util.VarUtils;
import pub.qiuf.litemc.core.context.GameContext;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ServerEventEncoder extends MessageToByteEncoder<Packet> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final GameContext gameCtx;

    public ServerEventEncoder(GameContext gameCtx) {
        this.gameCtx = gameCtx;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet clientEvent, ByteBuf out) throws Exception {
        byte[] msg = clientEvent.encode();

        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        if (gameCtx.getThreshold() < 0) { // 不压缩
            packet.write(VarUtils.encodeVarInt(msg.length));
            packet.write(msg);
        } else { // 压缩
            if (gameCtx.getThreshold() >= msg.length) { // 未超过最大长度
                packet.write(VarUtils.encodeVarInt(msg.length + 1));
                packet.write(VarUtils.encodeVarInt(0));
                packet.write(msg);
            } else {
                // TODO compress
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
