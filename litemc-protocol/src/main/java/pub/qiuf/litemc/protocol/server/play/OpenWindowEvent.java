package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x13)
public class OpenWindowEvent extends ServerEvent {

    private short windowId;
    private String windowType;
    private String windowTitle;
    private int numberOfSlots;
    private int entityId;

    public short getWindowId() {
        return windowId;
    }

    public void setWindowId(short windowId) {
        this.windowId = windowId;
    }

    public String getWindowType() {
        return windowType;
    }

    public void setWindowType(String windowType) {
        this.windowType = windowType;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setWindowId(lmis.readByte());
        setWindowType(lmis.readString());
        setWindowTitle(lmis.readString());
        setNumberOfSlots(lmis.readUnsignedByte());
        if ("EntityHorse".equals(windowType)) {
            setEntityId(lmis.readInt());
        }
    }

}
