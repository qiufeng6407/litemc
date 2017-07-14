package pub.qiuf.litemc.protocol.client.play;

import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.CommUtils;
import pub.qiuf.litemc.common.util.VarUtils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class UpdateSignEvent extends ClientEvent {
    private long[] location;
    private String line1;
    private String line2;
    private String line3;
    private String line4;

    public UpdateSignEvent(long[] location, String line1, String line2, String line3, String line4) {
        super(0x19);
        this.location = location;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
    }

    public long[] getLocation() {
        return location;
    }

    public void setLocation(long[] location) {
        this.location = location;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getLine4() {
        return line4;
    }

    public void setLine4(String line4) {
        this.line4 = line4;
    }

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(packetId));
        packet.write(CommUtils.encodePosition(location));
        packet.write(CommUtils.encodeString(line1));
        packet.write(CommUtils.encodeString(line2));
        packet.write(CommUtils.encodeString(line3));
        packet.write(CommUtils.encodeString(line4));
        return packet.toByteArray();
    }

}
