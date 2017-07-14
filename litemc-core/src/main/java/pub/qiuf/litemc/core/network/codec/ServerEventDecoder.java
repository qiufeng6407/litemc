package pub.qiuf.litemc.core.network.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.util.VarUtils;
import pub.qiuf.litemc.common.util.ZLibUtils;
import pub.qiuf.litemc.core.network.MineCraftClient;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

public class ServerEventDecoder extends ChannelInboundHandlerAdapter {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final MineCraftClient mineCraftClient;
    private final ServerEventBuilder serverEventBuilder;

    public ServerEventDecoder(MineCraftClient mineCraftClient) throws Exception {
        Preconditions.checkNotNull(mineCraftClient);
        this.mineCraftClient = mineCraftClient;
        serverEventBuilder = new ServerEventBuilder(mineCraftClient);
        serverEventBuilder.loadEventClass();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        byte[] buf = (byte[]) msg;

        if (mineCraftClient.getThreshold() > 0) {
            int varLength = VarUtils.nextVarIntLength(buf);
            int packetLength = VarUtils.decodeVarInt(buf);
            if (packetLength != 0) {
                buf = ZLibUtils.decompress(Arrays.copyOfRange(buf, varLength, buf.length));
            } else {
                buf = Arrays.copyOfRange(buf, varLength, buf.length);
            }
        }

        Optional<ServerEvent> decodeReulst = serverEventBuilder.build(buf);
        if (decodeReulst.isPresent()) {
            mineCraftClient.handleServerEvent(decodeReulst.get());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
