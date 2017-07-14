package pub.qiuf.litemc.common.bean.window;

import com.google.common.base.Strings;

public enum WindowType {
    INVENTORY("minecraft:player_inventory"), CONTAINER("minecraft:container"), CHEST("minecraft:chest"), CRAFTING_TABLE("minecraft:crafting_table"),

    ;
    private String desc;

    private WindowType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static WindowType getWindowTypeByDesc(String desc) {
        if (Strings.isNullOrEmpty(desc)) {
            return null;
        }
        for (WindowType windowType : values()) {
            if (windowType.desc.equals(desc)) {
                return windowType;
            }
        }
        return null;
    }
}
