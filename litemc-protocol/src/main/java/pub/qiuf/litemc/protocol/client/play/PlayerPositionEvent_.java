package pub.qiuf.litemc.protocol.client.play;

import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.VarUtils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class PlayerPositionEvent_ extends ClientEvent {
    private double x;
    private double feetY;
    private double z;
    private boolean onGround;

    public PlayerPositionEvent_(double x, double feetY, double z, boolean onGround) {
        super(0x0C);
        this.x = x;
        this.feetY = feetY;
        this.z = z;
        this.onGround = onGround;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getFeetY() {
        return feetY;
    }

    public void setFeetY(double feetY) {
        this.feetY = feetY;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(packetId));
        packet.writeDouble(x);
        packet.writeDouble(feetY);
        packet.writeDouble(z);
        packet.writeBoolean(onGround);
        return packet.toByteArray();
    }

}
