package pub.qiuf.litemc.common.bean;

import com.flowpowered.nbt.EndTag;
import com.flowpowered.nbt.Tag;
import com.google.common.base.MoreObjects;

public class Slot {
    private short blockId = -1;
    private int itemCount; // byte
    private short itemDamage;
    private Tag<?> tag;

    public Slot() {
    }

    public short getBlockId() {
        return blockId;
    }

    public void setBlockId(short blockId) {
        this.blockId = blockId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public short getItemDamage() {
        return itemDamage;
    }

    public void setItemDamage(short itemDamage) {
        this.itemDamage = itemDamage;
    }

    public Tag<?> getTag() {
        return tag;
    }

    public void setTag(Tag<?> tag) {
        this.tag = tag;
    }

    public Slot clone() {
        Slot slot = new Slot();
        slot.setBlockId(blockId);
        slot.setItemCount(itemCount);
        slot.setItemDamage(itemDamage);
        if (tag == null) {
            tag = new EndTag();
        }
        slot.setTag(tag.clone());
        return slot;
    }

    @Override
    public String toString() {
        if (blockId == -1) {
            return "Empty";
        }
        return MoreObjects.toStringHelper(this).add("blockId", blockId).add("itemCount", itemCount).toString();
    }
}
