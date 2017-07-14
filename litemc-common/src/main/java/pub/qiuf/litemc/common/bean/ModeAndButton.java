package pub.qiuf.litemc.common.bean;

public enum ModeAndButton {
    // mode 0
    LEFT_MOUSE_CLICK(0, (byte) 0), RIGHT_MOUSE_CLICK(0, (byte) 1),

    // mode 1
    SHIFT_LEFT_MOUSE_CLICK(1, (byte) 0), SHIFT_RIGHT_MOUSE_CLICK(1, (byte) 1),

    // mode 2
    NUMBER_KEY_1(2, (byte) 0), NUMBER_KEY_2(2, (byte) 1), NUMBER_KEY_3(2, (byte) 2), NUMBER_KEY_4(2, (byte) 3), NUMBER_KEY_5(2, (byte) 4), NUMBER_KEY_6(
            2, (byte) 5), NUMBER_KEY_7(2, (byte) 6), NUMBER_KEY_8(2, (byte) 7), NUMBER_KEY_9(2, (byte) 8),

    // mode 4
    DROP_KEY(4, (byte) 0), CTRL_DROP_KEY(4, (byte) 1),

    ;
    private int mode;
    private byte button;

    private ModeAndButton(int mode, byte button) {
        this.button = button;
        this.mode = mode;
    }

    public byte getButton() {
        return button;
    }

    public int getMode() {
        return mode;
    }
}
