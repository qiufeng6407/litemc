package pub.qiuf.litemc.protocol.client.play;

import pub.qiuf.litemc.common.bean.Slot;
import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.CommUtils;
import pub.qiuf.litemc.common.util.VarUtils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ClickWindowEvent extends ClientEvent {

    private short windowId;
    private short slot;
    private byte button;
    private short actionNumber;
    private int mode;
    private Slot clickedItem;

    public ClickWindowEvent(short windowId, short slot, byte button, short actionNumber, int mode, Slot clickedItem) {
        super(0x07);
        this.windowId = windowId;
        this.slot = slot;
        this.button = button;
        this.actionNumber = actionNumber;
        this.mode = mode;
        this.clickedItem = clickedItem;
    }

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

    public byte getButton() {
        return button;
    }

    public void setButton(byte button) {
        this.button = button;
    }

    public short getActionNumber() {
        return actionNumber;
    }

    public void setActionNumber(short actionNumber) {
        this.actionNumber = actionNumber;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public Slot getClickedItem() {
        return clickedItem;
    }

    public void setClickedItem(Slot clickedItem) {
        this.clickedItem = clickedItem;
    }

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(packetId));
        packet.writeByte(windowId);
        packet.writeShort(slot);
        packet.writeByte(button);
        packet.writeShort(actionNumber);
        packet.write(VarUtils.encodeVarInt(mode));
        packet.write(CommUtils.encodeSlot(clickedItem));
        return packet.toByteArray();
    }

}
