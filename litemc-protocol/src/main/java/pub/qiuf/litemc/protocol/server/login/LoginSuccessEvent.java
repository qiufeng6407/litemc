package pub.qiuf.litemc.protocol.server.login;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x02)
public class LoginSuccessEvent extends ServerEvent {

    private String uUID;
    private String username;

    public String getUUID() {
        return uUID;
    }

    public void setUUID(String uUID) {
        this.uUID = uUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setUUID(lmis.readString());
        setUsername(lmis.readString());
    }

}
