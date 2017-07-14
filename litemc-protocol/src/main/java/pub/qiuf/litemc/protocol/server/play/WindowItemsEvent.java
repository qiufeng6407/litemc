package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.bean.Slot;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x14)
public class WindowItemsEvent extends ServerEvent {

    private short windowId;
    private short count;
    private Slot[] slotData;

    public short getWindowId() {
        return windowId;
    }

    public void setWindowId(short windowId) {
        this.windowId = windowId;
    }

    public short getCount() {
        return count;
    }

    public void setCount(short count) {
        this.count = count;
    }

    public Slot[] getSlotData() {
        return slotData;
    }

    public void setSlotData(Slot[] slotData) {
        this.slotData = slotData;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setWindowId(lmis.readByte());
        setCount(lmis.readShort());
        slotData = new Slot[count];
        for (short i = 0; i < count; i++) {
            slotData[i] = lmis.readSlot();
        }
    }

}
