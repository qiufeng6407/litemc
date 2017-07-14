package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.bean.Slot;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x16)
public class SetSlotEvent extends ServerEvent {

    private short windowId;
    private short slot;
    private Slot slotData;

    public short getWindowId() {
        return windowId;
    }

    public void setWindowId(short windowId) {
        this.windowId = windowId;
    }

    public short getSlot() {
        return slot;
    }

    public void setSlot(short slot) {
        this.slot = slot;
    }

    public Slot getSlotData() {
        return slotData;
    }

    public void setSlotData(Slot slotData) {
        this.slotData = slotData;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setWindowId(lmis.readByte());
        setSlot(lmis.readShort());
        slotData = lmis.readSlot();
    }

}
