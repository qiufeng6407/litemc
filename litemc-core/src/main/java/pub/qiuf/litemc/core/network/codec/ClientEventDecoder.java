package pub.qiuf.litemc.core.network.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.common.bean.datatype.Packet;
import pub.qiuf.litemc.common.util.VarUtils;
import pub.qiuf.litemc.common.util.ZLibUtils;
import pub.qiuf.litemc.core.context.GameContext;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import pub.qiuf.litemc.protocol.login.client.Login_Client_SetCompression;

public class ClientEventDecoder extends ChannelInboundHandlerAdapter {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final ClientEventBuilder serverEventBuilder;

    private final GameContext gameCtx;

    public ClientEventDecoder(GameContext gameCtx) throws Exception {
        Preconditions.checkNotNull(gameCtx);
        this.gameCtx = gameCtx;
        serverEventBuilder = new ClientEventBuilder(gameCtx);
        serverEventBuilder.loadEventClass();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        byte[] buf = (byte[]) msg;

        if (gameCtx.getThreshold() > 0) {
            int varLength = VarUtils.nextVarIntLength(buf);
            int dataLength = VarUtils.decodeVarInt(buf);
            if (dataLength != 0) {
                buf = ZLibUtils.decompress(Arrays.copyOfRange(buf, varLength, buf.length));
            } else {
                buf = Arrays.copyOfRange(buf, varLength, buf.length);
            }
        }

        Optional<Packet> decodeReulst = serverEventBuilder.build(buf);
        if (decodeReulst.isPresent()) {
            Packet event = decodeReulst.get();
            if (event instanceof Login_Client_SetCompression) {
                Login_Client_SetCompression setCompression = (Login_Client_SetCompression) event;
                gameCtx.setThreshold(setCompression.getThreshold());
                logger.info("set compression: {}", setCompression.getThreshold());
            } else {
                gameCtx.getClientEventQueue().offer(event);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
