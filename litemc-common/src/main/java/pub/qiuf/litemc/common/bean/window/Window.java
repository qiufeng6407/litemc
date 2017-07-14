package pub.qiuf.litemc.common.bean.window;

import com.google.common.base.Optional;

import pub.qiuf.litemc.common.bean.Slot;

public interface Window {
    short getWindowId();

    WindowType getWindowType();

    String getWindowTitle();

    int getNumberOfSlots();

    int getEntityId();

    Optional<Slot> getCursorSlot() throws Exception;

    void setCursorSlot(Slot slot) throws Exception;

    void setWindowSlot(short slotIndex, Slot slot) throws Exception;

    void setWindowSlot(Slot[] slots) throws Exception;

    boolean isWindowSlotSeted();

    boolean isEmpty(short slotIndex);

    Optional<Slot> getSlot(short slotIndex) throws Exception;

    boolean exchange(short slotIndexFrom, short slotIndexTo) throws Exception;

    /**
     * 
     * @param blockId
     * @return index of the slot
     */
    short findByBlockId(short blockId);
}
