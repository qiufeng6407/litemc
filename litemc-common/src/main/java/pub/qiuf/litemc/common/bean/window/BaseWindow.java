package pub.qiuf.litemc.common.bean.window;

import java.util.concurrent.locks.ReentrantLock;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Optional;

import pub.qiuf.litemc.common.bean.Slot;

public class BaseWindow implements Window {

    protected short windowId;
    protected WindowType windowType;
    protected String windowTitle;
    protected int numberOfSlots;
    protected int entityId;
    protected Slot cursorSlot;
    protected Slot[] windowSlots;
    protected boolean windowSlotSeted = false;

    protected ReentrantLock windowLock = new ReentrantLock();

    public BaseWindow(short windowId, WindowType windowType, String windowTitle, int numberOfSlots, int entityId) {
        this.windowId = windowId;
        this.windowType = windowType;
        this.windowTitle = windowTitle;
        this.numberOfSlots = numberOfSlots;
        this.entityId = entityId;
        this.windowSlots = new Slot[numberOfSlots + 45];
    }

    @Override
    public short getWindowId() {
        return windowId;
    }

    @Override
    public WindowType getWindowType() {
        return windowType;
    }

    @Override
    public String getWindowTitle() {
        return windowTitle;
    }

    @Override
    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    @Override
    public int getEntityId() {
        return entityId;
    }

    @Override
    public Optional<Slot> getCursorSlot() throws Exception {
        return Optional.fromNullable(cursorSlot);
    }

    @Override
    public void setWindowSlot(short slotIndex, Slot slot) throws Exception {
        windowLock.lock();
        try {
            windowSlots[slotIndex] = slot.clone();
        } finally {
            windowLock.unlock();
        }
    }

    @Override
    public void setWindowSlot(Slot[] slots) throws Exception {
        windowLock.lock();
        try {
            for (short slotIndex = 0; slotIndex < slots.length; slotIndex++) {
                windowSlots[slotIndex] = slots[slotIndex].clone();
            }
            windowSlotSeted = true;
        } finally {
            windowLock.unlock();
        }
    }

    @Override
    public void setCursorSlot(Slot slot) throws Exception {
        windowLock.lock();
        try {
            cursorSlot = slot;
        } finally {
            windowLock.unlock();
        }
    }

    @Override
    public boolean isWindowSlotSeted() {
        return windowSlotSeted;
    }

    @Override
    public boolean isEmpty(short slotIndex) {
        if (this.windowSlots == null || slotIndex >= this.windowSlots.length) {
            throw new IllegalStateException("set player inventory failed!");
        }
        if (this.windowSlots[slotIndex] == null) {
            return true;
        }
        if (this.windowSlots[slotIndex].getBlockId() == -1) {
            return true;
        }
        return false;
    }

    @Override
    public short findByBlockId(short blockId) {
        if (this.windowSlots == null) {
            return -1;
        }
        for (short i = 0; i < windowSlots.length; i++) {
            if (windowSlots[i] == null) {
                windowSlots[i] = new Slot();
                continue;
            }
            if (windowSlots[i].getBlockId() == blockId) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Optional<Slot> getSlot(short slotIndex) throws Exception {
        if (this.windowSlots == null || slotIndex >= this.windowSlots.length) {
            throw new IllegalStateException("set player inventory failed!");
        }
        return Optional.fromNullable(this.windowSlots[slotIndex]);
    }

    @Override
    public boolean exchange(short slotIndexFrom, short slotIndexTo) throws Exception {
        if (this.windowSlots == null || slotIndexFrom >= this.windowSlots.length || slotIndexTo >= this.windowSlots.length) {
            throw new IllegalStateException("exchange failed!");
        }
        windowLock.lock();
        Slot tempSlot = windowSlots[slotIndexTo];
        windowSlots[slotIndexTo] = windowSlots[slotIndexFrom];
        windowSlots[slotIndexFrom] = tempSlot;

        windowLock.unlock();
        return true;
    }

    @Override
    public String toString() {
        ToStringHelper toStringHelper = MoreObjects.toStringHelper(this);
        toStringHelper.add("windowId", windowId);
        toStringHelper.add("windowType", windowType);
        toStringHelper.add("windowTitle", windowTitle);
        toStringHelper.add("numberOfSlots", numberOfSlots);
        if (windowSlots == null) {
            toStringHelper.add("windowSlots", "Empty");
        } else {
            for (int i = 0; i < windowSlots.length; i++) {
                toStringHelper.add("s" + i, windowSlots[i]);
            }
        }
        return super.toString();
    }

}
