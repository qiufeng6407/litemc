package pub.qiuf.litemc.protocol.client.play;

import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.CommUtils;
import pub.qiuf.litemc.common.util.VarUtils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class PlayerDiggingEvent extends ClientEvent {
    private int status;
    private long[] location;
    private byte face;

    public PlayerDiggingEvent(int status, long[] location, byte face) {
        super(0x13);
        this.status = status;
        this.location = location;
        this.face = face;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long[] getLocation() {
        return location;
    }

    public void setLocation(long[] location) {
        this.location = location;
    }

    public byte getFace() {
        return face;
    }

    public void setFace(byte face) {
        this.face = face;
    }

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(packetId));
        packet.write(VarUtils.encodeVarInt(status));
        packet.write(CommUtils.encodePosition(location));
        packet.write(face);
        return packet.toByteArray();
    }

}
