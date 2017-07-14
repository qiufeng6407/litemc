package pub.qiuf.litemc.common.bean;

/**
 * 0: main hand 1: off hand
 */
public enum Hand {
    MAIN_HAND(0), OFF_HAND(1);
    private int value;

    private Hand(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
