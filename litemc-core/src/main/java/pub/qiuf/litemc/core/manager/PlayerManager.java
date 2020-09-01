package pub.qiuf.litemc.core.manager;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.common.bean.ClientState;
import pub.qiuf.litemc.common.bean.window.PlayerInventory;
import pub.qiuf.litemc.common.bean.window.WindowAction;
import pub.qiuf.litemc.core.context.GameContext;
import pub.qiuf.litemc.core.network.MineCraftClient;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import pub.qiuf.litemc.protocol.login.client.Login_Client_Disconnect;
import pub.qiuf.litemc.protocol.play.client.Play_Client_JoinGame;
import pub.qiuf.litemc.protocol.play.client.Play_Client_PlayerPositionAndLook;
import pub.qiuf.litemc.protocol.play.client.Play_Client_UpdateHealth;

import java.util.UUID;

@Getter
public class PlayerManager {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Setter
    private String uuid;
    private int gamemode;
    private float health;
    private int food;
    private float foodSaturation;
    private int dimension;
    /** 初始死亡 */
    @Setter
    private boolean isDied = true;

    protected final GameContext gameCtx;

    public PlayerManager(GameContext gameCtx) {
        this.gameCtx = gameCtx;
    }

    @Subscribe
    public void on_Play_Client_JoinGame(Play_Client_JoinGame event) throws Exception {
        logger.info("[Play JoinGame]");
        this.gamemode = event.getGamemode();
        this.dimension = event.getDimension();
    }

    @Subscribe
    public void on_Play_Client_UpdateHealth(Play_Client_UpdateHealth event) throws Exception {
        this.health = event.getHealth();
        this.food = event.getFood();
        this.foodSaturation = event.getFoodSaturation();
        if (this.health == 0) {
            isDied = true;
        }
    }

    /*

    protected PlayerInventory inventory = new PlayerInventory();
    protected WindowAction windowAction;
    protected boolean isPlayerDied = false;

    protected final MineCraftClient mineCraftClient;

    public PlayerManager(MineCraftClient mineCraftClient) {
        Preconditions.checkNotNull(mineCraftClient);
        this.mineCraftClient = mineCraftClient;
    }

    public boolean hasUnConfirmedWindowAction() throws Exception {
        if (windowAction == null) {
            return false;
        }
        return !windowAction.isConfirmed();
    }

    public void clearWindowActionIfConfirmed() throws Exception {
        if (hasUnConfirmedWindowAction()) {
            return;
        }
        windowAction = null;
    }

    public boolean windowAction(WindowAction windowAction) throws Exception {
        if (windowAction == null) {
            return false;
        }
        if (hasUnConfirmedWindowAction()) {
            return false;
        }
        if (windowAction.getWindowId() != 0) {
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
    public void onJoinGame(JoinGameEvent serverEvent) throws Exception {
        logger.info("player join game.");
    }

    @Subscribe
    public void onDisconnect(DisconnectEvent serverEvent) throws Exception {
        mineCraftClient.playerDisconnect("[play] " + serverEvent.getReason());
    }

    @Subscribe
    public void onUpdateHealth(UpdateHealthEvent event) throws Exception {
        if (event.getHealth() <= 0) {
            logger.info("player died.");
            isPlayerDied = true;
        }
    }

    @Subscribe
    public void onRespawn(RespawnEvent event) throws Exception {
        isPlayerRespawning = true;
        if (isPlayerDied) {
            isPlayerDied = false;
        }
    }

    @Subscribe
    public void onPlayerPositionAndLook(PlayerPositionAndLookEvent serverEvent) throws Exception {
        if (isPlayerRespawning) {
            isPlayerRespawning = false;
        }
    }

    @Subscribe
    public void onKeepAlive(KeepAliveEvent serverEvent) throws Exception {
        mineCraftClient.postClientEvent(new KeepAliveEvent_(serverEvent.getKeepAliveId()));
    }

    @Subscribe
    public void onWindowItems(WindowItemsEvent event) throws Exception {
        if (event.getWindowId() != 0) {
            return;
        }
        inventory.setWindowSlot(event.getSlotData());
        logger.info("set window items {}", event.getWindowId());
    }

    @Subscribe
    public void onSetSlot(SetSlotEvent event) throws Exception {
        if (event.getWindowId() != 0) {
            return;
        }
        if (event.getWindowId() == -1 && event.getSlot() == -1) {
            inventory.setCursorSlot(event.getSlotData());
        } else {
            inventory.setWindowSlot(event.getSlot(), event.getSlotData());
        }
    }

    @Subscribe
    public void onConfirmTransaction(ConfirmTransactionEvent serverEvent) throws Exception {
        logger.info("confirm transaction, {}, {}", serverEvent.getWindowId(), serverEvent.isAccepted());
        if (serverEvent.getWindowId() != 0) {
            return;
        }
        mineCraftClient.postClientEvent(new ConfirmTransactionEvent_(serverEvent.getWindowId(), serverEvent.getActionNumber(), true));

        if (windowAction == null) {
            return;
        }
        windowAction.setConfirmed(true);
        windowAction.setAccepted(serverEvent.isAccepted());
    }

    public boolean isPlayerRespawning() {
        return isPlayerRespawning;
    }

    public boolean isPlayerDied() {
        return isPlayerDied;
    }

    public Optional<WindowAction> getWindowAction() {
        return Optional.fromNullable(windowAction);
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

     */
}
