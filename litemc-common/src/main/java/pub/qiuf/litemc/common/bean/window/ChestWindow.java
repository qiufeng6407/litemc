package pub.qiuf.litemc.common.bean.window;

public class ChestWindow extends BaseWindow {

    public ChestWindow(short windowId, String windowTitle, int numberOfSlots) {
        super(windowId, WindowType.CHEST, windowTitle, numberOfSlots, -1);
    }

}
