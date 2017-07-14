package pub.qiuf.litemc.common.bean;

/**
 * 0 -Y Bottom 1 +Y Top 2 -Z North 3 +Z South 4 -X West 5 +X East 255 — Special
 */
public enum Face {
    BOTTOM(0), TOP(1), NORTH(2), SOUTH(3), WEST(4), EAST(5), SPECIAL(255);
    private int value;

    private Face(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
