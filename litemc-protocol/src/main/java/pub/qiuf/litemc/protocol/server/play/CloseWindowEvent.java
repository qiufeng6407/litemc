package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x12)
public class CloseWindowEvent extends ServerEvent {

    private short windowId;

    public short getWindowId() {
        return windowId;
    }

    public void setWindowId(short windowId) {
        this.windowId = windowId;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setWindowId(lmis.readByte());
    }

}
