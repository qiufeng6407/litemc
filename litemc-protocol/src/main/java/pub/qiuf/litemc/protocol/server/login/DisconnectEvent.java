package pub.qiuf.litemc.protocol.server.login;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x00)
public class DisconnectEvent extends ServerEvent {

    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setReason(lmis.readString());
    }

}
