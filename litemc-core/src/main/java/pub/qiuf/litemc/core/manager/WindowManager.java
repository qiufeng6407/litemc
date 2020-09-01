package pub.qiuf.litemc.core.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.common.bean.window.ChestWindow;
import pub.qiuf.litemc.common.bean.window.Window;
import pub.qiuf.litemc.common.bean.window.WindowAction;
import pub.qiuf.litemc.common.bean.window.WindowType;
import pub.qiuf.litemc.core.context.GameContext;
import pub.qiuf.litemc.core.network.MineCraftClient;

import com.google.common.eventbus.Subscribe;

public class WindowManager {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected final GameContext gameCtx;

    public WindowManager(GameContext gameCtx) {
        this.gameCtx = gameCtx;
    }
    /*

    protected Window window;
    protected WindowAction windowAction;

    protected final MineCraftClient mineCraftClient;

    public WindowManager(MineCraftClient mineCraftClient) {
        this.mineCraftClient = mineCraftClient;
    }

    public boolean hasUnConfirmedWindowAction() throws Exception {
        if (windowAction == null) {
            return false;
        }
        return !windowAction.isConfirmed();
    }

    public boolean clearWindowActionIfConfirmed() throws Exception {
        if (windowAction == null) {
            return true;
        }
        if (!windowAction.isConfirmed()) {
            return false;
        }
        windowAction = null;
        return true;
    }

    public boolean windowAction(WindowAction windowAction) throws Exception {
        if (windowAction == null) {
            return false;
        }
        if (window == null) {
            return false;
        }
        if (this.windowAction != null && !this.windowAction.isConfirmed()) {
            return false;
        }
        if (windowAction.getWindowId() != window.getWindowId()) {
            return false;
        }
        windowAction.setActionNumber(mineCraftClient.getActionNumber());
        this.windowAction = windowAction;
        mineCraftClient.postClientEvent(new ClickWindowEvent(windowAction.getWindowId(), //
                windowAction.getSlotIndex(), //
                windowAction.getModeAndButton().getButton(), //
                windowAction.getActionNumber(), //
                windowAction.getModeAndButton().getMode(), //
                windowAction.getClickedItem()));
        return true;
    }

    @Subscribe
    public void onCloseWindow(CloseWindowEvent event) throws Exception {
        logger.info("window closed, {}", event.getWindowId());
        if (event.getWindowId() == 0) {
            return;
        }
        if (window == null) {
            return;
        }
        if (window.getWindowId() != event.getWindowId()) {
            return;
        }
        window = null;
    }

    @Subscribe
    public void onOpenWindow(OpenWindowEvent event) throws Exception {
        logger.info("window opened, {}, {}, {}", event.getWindowId(), event.getWindowType(), event.getNumberOfSlots());
        WindowType windowType = WindowType.getWindowTypeByDesc(event.getWindowType());
        if (windowType == null) {
            return;
        }
        switch (windowType) {
        case CHEST:
        case CONTAINER:
            window = new ChestWindow(event.getWindowId(), event.getWindowTitle(), event.getNumberOfSlots());
            break;
        default:
            break;
        }
    }

    @Subscribe
    public void onWindowItems(WindowItemsEvent event) throws Exception {
        if (event.getWindowId() == 0) {
            return;
        } else {
            if (window == null) {
                return;
            }
            if (event.getWindowId() != window.getWindowId()) {
                return;
            }
            window.setWindowSlot(event.getSlotData());
        }
        logger.info("set window items {}", event.getWindowId());
    }

    @Subscribe
    public void onSetSlot(SetSlotEvent event) throws Exception {
        if (event.getWindowId() == 0) {
            return;
        } else {
            if (window == null) {
                return;
            }
            if (event.getWindowId() == -1 && event.getSlot() == -1) {
                window.setCursorSlot(event.getSlotData());
            } else if (event.getWindowId() == window.getWindowId()) {
                window.setWindowSlot(event.getSlot(), event.getSlotData());
            } else {
                // should not come here
            }
        }
    }

    @Subscribe
    public void onConfirmTransaction(ConfirmTransactionEvent serverEvent) throws Exception {
        logger.info("confirm transaction, {}, {}", serverEvent.getWindowId(), serverEvent.isAccepted());
        if (serverEvent.getWindowId() == 0) {
            return;
        }
        mineCraftClient.postClientEvent(new ConfirmTransactionEvent_(serverEvent.getWindowId(), serverEvent.getActionNumber(), true));
        if (window == null) {
            return;
        }
        if (window.getWindowId() != serverEvent.getWindowId()) {
            return;
        }
        if (windowAction == null) {
            return;
        }
        if (windowAction.getWindowId() != serverEvent.getWindowId()) {
            return;
        }
        windowAction.setConfirmed(true);
        windowAction.setAccepted(serverEvent.isAccepted());
    }

    public Window getWindow() {
        return window;
    }

    public WindowAction getWindowAction() {
        return windowAction;
    }
    */

}
