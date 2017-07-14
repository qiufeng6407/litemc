package pub.qiuf.litemc.core.manager;

import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.core.network.MineCraftClient;
import pub.qiuf.litemc.protocol.client.play.PlayerEvent_;
import pub.qiuf.litemc.protocol.client.play.PlayerLookEvent_;
import pub.qiuf.litemc.protocol.client.play.PlayerPositionAndLookEvent_;
import pub.qiuf.litemc.protocol.client.play.PlayerPositionEvent_;
import pub.qiuf.litemc.protocol.client.play.TeleportConfirmEvent;
import pub.qiuf.litemc.protocol.server.login.LoginSuccessEvent;
import pub.qiuf.litemc.protocol.server.play.PlayerPositionAndLookEvent;

import com.google.common.base.MoreObjects;
import com.google.common.eventbus.Subscribe;

public class PositionManager {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected double x = 0;
    protected double feetY = 0;
    protected double z = 0;
    protected float yaw = 0;
    protected float pitch = 0;
    protected boolean onGround = false;
    protected long stationaryTick = 0;
    protected boolean isPlayerPostionChanged = false;
    protected boolean isPlayerLookChanged = false;
    protected ReentrantLock playerPositionAndLookLock = new ReentrantLock(true);

    protected final MineCraftClient mineCraftClient;

    public PositionManager(MineCraftClient mineCraftClient) {
        this.mineCraftClient = mineCraftClient;
    }

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

    public double getX() {
        return x;
    }

    public double getFeetY() {
        return feetY;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public boolean isOnGround() {
        return onGround;
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

}
