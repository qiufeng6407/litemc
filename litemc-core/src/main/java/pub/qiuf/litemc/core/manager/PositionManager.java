package pub.qiuf.litemc.core.manager;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.common.bean.ClientState;
import pub.qiuf.litemc.core.context.GameContext;

import com.google.common.base.MoreObjects;
import com.google.common.eventbus.Subscribe;
import pub.qiuf.litemc.protocol.login.client.Login_Client_LoginSuccess;
import pub.qiuf.litemc.protocol.play.client.Play_Client_PlayerPositionAndLook;
import pub.qiuf.litemc.protocol.play.server.Play_Server_ClientStatus;
import pub.qiuf.litemc.protocol.play.server.Play_Server_PlayerPosition;
import pub.qiuf.litemc.protocol.play.server.Play_Server_PlayerPositionAndRotation;
import pub.qiuf.litemc.protocol.play.server.Play_Server_TeleportConfirm;

@Getter
public class PositionManager implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final GameContext gameCtx;

    private double x = 0;
    private double feetY = 0;
    private double z = 0;
    private float yaw = 0;
    private float pitch = 0;
    private boolean onGround = false;
    private long staticClientTick = 0; // 静止tick数
    private boolean isPlayerPostionChanged = false;
    private boolean isPlayerLookChanged = false;

    private ReentrantLock lock = new ReentrantLock();
    private Condition syncCondition = lock.newCondition();

    public PositionManager(GameContext gameCtx) {
        this.gameCtx = gameCtx;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            try {
                while (gameCtx.getClientState() != ClientState.DISCONNECT) {
                    if (gameCtx.getClientState() != ClientState.PLAY || gameCtx.getPlayerManager().isDied()
                            || !isPlayerPostionChanged && !isPlayerLookChanged && staticClientTick < 20) {
                        syncCondition.await();
                        continue;
                    }
                    if (!isPlayerPostionChanged && !isPlayerLookChanged) {
                        gameCtx.getServerEventQueue().add(Play_Server_PlayerPosition.builder().x(x).feetY(feetY).z(z).onGround(onGround).build());
                    } else {
                        gameCtx.getServerEventQueue().add(Play_Server_PlayerPositionAndRotation.builder().x(x).feetY(feetY).z(z).yaw(yaw).pitch(pitch).onGround(onGround).build());
                    }
                    staticClientTick = 0;
                    isPlayerPostionChanged = false;
                    isPlayerLookChanged = false;
                }
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            logger.error("PositionManager Interrupted");
        }
    }

    @Subscribe
    public void clientTickSubscriber(Long clientTick) {
        lock.lock();
        try {
            staticClientTick++;
            if (staticClientTick >= 20) {
                syncCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    @Subscribe
    public void on_Play_Client_PlayerPositionAndLook(Play_Client_PlayerPositionAndLook event) {
        lock.lock();
        try {
            this.x = (event.getFlags() & 0x01) > 0 ? this.x + event.getX() : event.getX();
            this.feetY = (event.getFlags() & 0x02) > 0 ? this.feetY + event.getY() : event.getY();
            this.z = (event.getFlags() & 0x04) > 0 ? this.z + event.getZ() : event.getZ();
            this.yaw = (event.getFlags() & 0x08) > 0 ? this.yaw + event.getYaw() : event.getYaw();
            this.pitch = (event.getFlags() & 0x10) > 0 ? this.pitch + event.getPitch() : event.getPitch();
            this.onGround = ((int) (feetY) % 100) == 0;
            if (gameCtx.getPlayerManager().isDied()) {
                gameCtx.getPlayerManager().setDied(false);
            }
        } finally {
            lock.unlock();
        }
        logger.info("[Player Position And Look] ({}, {}, {}, {}, {})", this.x, this.feetY, this.z, this.yaw, this.pitch);
        gameCtx.getServerEventQueue().offer(Play_Server_TeleportConfirm.builder().teleportId(event.getTeleportId()).build());
        gameCtx.getServerEventQueue().offer(Play_Server_PlayerPositionAndRotation.builder().x(x).feetY(feetY).z(z).yaw(yaw).pitch(pitch).onGround(onGround).build());
        gameCtx.getServerEventQueue().offer(Play_Server_ClientStatus.builder().actionId(0).build());
    }

    /*
    public void clientTickMovement(long clientTick) throws Exception {
        playerPositionAndLookLock.lock();
        try {
            logger.debug("send {}, {}, {}, {}, {}, {}", x, feetY, z, yaw, pitch, onGround);
            // server bound movement packets
            if (isPlayerPostionChanged && isPlayerLookChanged) {
                mineCraftClient.postClientEvent(new PlayerPositionAndLookEvent_(x, feetY, z, yaw, pitch, onGround));
                isPlayerPostionChanged = false;
                isPlayerLookChanged = false;
                stationaryTick = 0;
            } else if (isPlayerPostionChanged && !isPlayerLookChanged) {
                mineCraftClient.postClientEvent(new PlayerPositionEvent_(x, feetY, z, onGround));
                isPlayerPostionChanged = false;
                stationaryTick = 0;
            } else if (!isPlayerPostionChanged && isPlayerLookChanged) {
                mineCraftClient.postClientEvent(new PlayerLookEvent_(yaw, pitch, onGround));
                isPlayerLookChanged = false;
                stationaryTick = 0;
            } else {
                stationaryTick++;

                // send position every 20 tick even for a stationary player
                if (stationaryTick == 20) {
                    mineCraftClient.postClientEvent(new PlayerPositionEvent_(x, feetY, z, onGround));
                    stationaryTick = 0;
                } else {
                    mineCraftClient.postClientEvent(new PlayerEvent_(onGround));
                }
            }
        } finally {
            playerPositionAndLookLock.unlock();
        }
    }

    public void setPlayerPositionAndLook(double x, double feetY, double z, float yaw, float pitch, boolean onGround) throws Exception {
        playerPositionAndLookLock.lock();
        setPlayerPosition(x, feetY, z, onGround);
        setYawAndPitch(yaw, pitch);
        playerPositionAndLookLock.unlock();
    }

    public void horizontalRelativeMove(double dx, double dz) throws Exception {
        playerPositionAndLookLock.lock();
        this.x = x + dx;
        this.z = z + dz;
        this.isPlayerPostionChanged = true;
        playerPositionAndLookLock.unlock();
    }

    public void setPlayerPosition(double x, double feetY, double z, boolean onGround) throws Exception {
        playerPositionAndLookLock.lock();
        this.x = x;
        this.feetY = feetY;
        this.z = z;
        this.onGround = onGround;
        this.isPlayerPostionChanged = true;
        playerPositionAndLookLock.unlock();
    }

    public void setYawAndPitch(float yaw, float pitch) {
        playerPositionAndLookLock.lock();
        this.yaw = yaw;
        this.pitch = pitch;
        this.isPlayerLookChanged = true;
        playerPositionAndLookLock.unlock();
    }

    @Subscribe
    public void onLoginSuccess(LoginSuccessEvent serverEvent) throws Exception {
        setPlayerPositionAndLook(0, 0, 0, 0, 0, false);
    }

    @Subscribe
    public void onPlayerPositionAndLook(PlayerPositionAndLookEvent serverEvent) throws Exception {
        mineCraftClient.postClientEvent(new TeleportConfirmEvent(serverEvent.getTeleportId()));
        onGround = ((int) (serverEvent.getAbsoluteFeetY(feetY) * 100)) % 100 == 0;
        setPlayerPositionAndLook(serverEvent.getAbsoluteX(x), serverEvent.getAbsoluteFeetY(feetY), serverEvent.getAbsoluteZ(z),
                serverEvent.getAbsoluteYaw(yaw), serverEvent.getAbsolutePitch(pitch), onGround);
        logger.info("reset pl {}, {}, {}, {}, {}, {}", x, feetY, z, yaw, pitch, onGround);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("x", x).add("feetY", feetY).add("z", z).add("yaw", yaw).add("pitch", pitch).toString();
    }
    */

}
