package pub.qiuf.litemc.protocol.client.play;

import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.CommUtils;
import pub.qiuf.litemc.common.util.VarUtils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class PlayerBlockPlacementEvent extends ClientEvent {
    private long[] location;
    private int face;
    private int hand;
    private float cursorPositionX;
    private float cursorPositionY;
    private float cursorPositionZ;

    public PlayerBlockPlacementEvent(long[] location, int face, int hand, float cursorPositionX, float cursorPositionY, float cursorPositionZ) {
        super(0x1C);
        this.location = location;
        this.face = face;
        this.hand = hand;
        this.cursorPositionX = cursorPositionX;
        this.cursorPositionY = cursorPositionY;
        this.cursorPositionZ = cursorPositionZ;
    }

    public long[] getLocation() {
        return location;
    }

    public void setLocation(long[] location) {
        this.location = location;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getHand() {
        return hand;
    }

    public void setHand(int hand) {
        this.hand = hand;
    }

    public float getCursorPositionX() {
        return cursorPositionX;
    }

    public void setCursorPositionX(float cursorPositionX) {
        this.cursorPositionX = cursorPositionX;
    }

    public float getCursorPositionY() {
        return cursorPositionY;
    }

    public void setCursorPositionY(float cursorPositionY) {
        this.cursorPositionY = cursorPositionY;
    }

    public float getCursorPositionZ() {
        return cursorPositionZ;
    }

    public void setCursorPositionZ(float cursorPositionZ) {
        this.cursorPositionZ = cursorPositionZ;
    }

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.writeByte(0x1C);
        packet.write(CommUtils.encodePosition(location));
        packet.write(VarUtils.encodeVarInt(face));
        packet.write(VarUtils.encodeVarInt(hand));
        packet.writeFloat(cursorPositionX);
        packet.writeFloat(cursorPositionY);
        packet.writeFloat(cursorPositionZ);
        return packet.toByteArray();
    }

}
