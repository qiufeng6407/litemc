package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x1F)
public class KeepAliveEvent extends ServerEvent {

    private int keepAliveId;

    public int getKeepAliveId() {
        return keepAliveId;
    }

    public void setKeepAliveId(int keepAliveId) {
        this.keepAliveId = keepAliveId;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setKeepAliveId(lmis.readVarInt());
    }

}
