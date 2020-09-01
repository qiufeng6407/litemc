package pub.qiuf.litemc.common.bean.window;

import pub.qiuf.litemc.common.bean.ModeAndButton;
import pub.qiuf.litemc.common.bean.datatype.Slot;

public class WindowAction {
    private short windowId;
    private short slotIndex;
    private ModeAndButton modeAndButton;
    private Slot clickedItem;

    // set
    private short actionNumber;

    // return
    private boolean confirmed = false;
    private boolean accepted = false;

    public WindowAction(short windowId, short slotIndex, ModeAndButton modeAndButton, Slot clickedItem) {
        this.windowId = windowId;
        this.slotIndex = slotIndex;
        this.modeAndButton = modeAndButton;
        this.clickedItem = clickedItem;
    }

    public short getActionNumber() {
        return actionNumber;
    }

    public void setActionNumber(short actionNumber) {
        this.actionNumber = actionNumber;
    }

    public short getSlotIndex() {
        return slotIndex;
    }

    public void setSlotIndex(short slotIndex) {
        this.slotIndex = slotIndex;
    }

    public short getWindowId() {
        return windowId;
    }

    public void setWindowId(short windowId) {
        this.windowId = windowId;
    }

    public ModeAndButton getModeAndButton() {
        return modeAndButton;
    }

    public void setModeAndButton(ModeAndButton modeAndButton) {
        this.modeAndButton = modeAndButton;
    }

    public Slot getClickedItem() {
        return clickedItem;
    }

    public void setClickedItem(Slot clickedItem) {
        this.clickedItem = clickedItem;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

}
