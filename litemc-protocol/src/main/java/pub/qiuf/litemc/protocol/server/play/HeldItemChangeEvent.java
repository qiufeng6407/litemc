package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x37)
public class HeldItemChangeEvent extends ServerEvent {

    private byte slot;

    public byte getSlot() {
        return slot;
    }

    public void setSlot(byte slot) {
        this.slot = slot;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setSlot(lmis.readByte());
    }

}
