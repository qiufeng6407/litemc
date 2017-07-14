package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x11)
public class ConfirmTransactionEvent extends ServerEvent {

    private byte windowId;
    private short actionNumber;
    private boolean accepted;

    public byte getWindowId() {
        return windowId;
    }

    public void setWindowId(byte windowId) {
        this.windowId = windowId;
    }

    public short getActionNumber() {
        return actionNumber;
    }

    public void setActionNumber(short actionNumber) {
        this.actionNumber = actionNumber;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setWindowId(lmis.readByte());
        setActionNumber(lmis.readShort());
        setAccepted(lmis.readBoolean());
    }

}
