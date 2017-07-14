package pub.qiuf.litemc.protocol.client.play;

import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.VarUtils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class CloseWindowEvent extends ClientEvent {

    private short windowId;

    public CloseWindowEvent(short windowId) {
        super(0x08);
        this.windowId = windowId;
    }

    public short getWindowId() {
        return windowId;
    }

    public void setWindowId(short windowId) {
        this.windowId = windowId;
    }

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(packetId));
        packet.writeByte(windowId);
        return packet.toByteArray();
    }

}
