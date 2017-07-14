package pub.qiuf.litemc.protocol.server.login;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x03)
public class SetCompressionEvent extends ServerEvent {

    private int threshold;

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setThreshold(lmis.readVarInt());
    }

}
