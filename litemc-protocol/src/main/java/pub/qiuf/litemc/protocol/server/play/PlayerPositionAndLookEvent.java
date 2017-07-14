package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x2e)
public class PlayerPositionAndLookEvent extends ServerEvent {

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private byte flags;
    private int teleportId;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public byte getFlags() {
        return flags;
    }

    public void setFlags(byte flags) {
        this.flags = flags;
    }

    public int getTeleportId() {
        return teleportId;
    }

    public void setTeleportId(int teleportId) {
        this.teleportId = teleportId;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setX(lmis.readDouble());
        setY(lmis.readDouble());
        setZ(lmis.readDouble());
        setYaw(lmis.readFloat());
        setPitch(lmis.readFloat());
        setFlags(lmis.readByte());
        setTeleportId(lmis.readVarInt());
    }

    // addtion method
    public double getAbsoluteX(double oldX) {
        if ((flags & 0x01) > 0) {
            return (oldX + x);
        } else {
            return x;
        }
    }

    public double getAbsoluteFeetY(double oldY) {
        if ((flags & 0x02) > 0) {
            return (oldY + y);
        } else {
            return y;
        }
    }

    public double getAbsoluteZ(double oldZ) {
        if ((flags & 0x04) > 0) {
            return (oldZ + z);
        } else {
            return z;
        }
    }

    public float getAbsoluteYaw(float oldYaw) {
        if ((flags & 0x08) > 0) {
            return (oldYaw + yaw);
        } else {
            return yaw;
        }
    }

    public float getAbsolutePitch(float oldPitch) {
        if ((flags & 0x10) > 0) {
            return (oldPitch + pitch);
        } else {
            return pitch;
        }
    }

}
