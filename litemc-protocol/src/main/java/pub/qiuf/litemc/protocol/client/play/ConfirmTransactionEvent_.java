package pub.qiuf.litemc.protocol.client.play;

import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.VarUtils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ConfirmTransactionEvent_ extends ClientEvent {
    private byte windowId;
    private short actionNumber;
    private boolean accepted;

    public ConfirmTransactionEvent_(byte windowId, short actionNumber, boolean accepted) {
        super(0x05);
        this.windowId = windowId;
        this.actionNumber = actionNumber;
        this.accepted = accepted;
    }

    public byte getWindowId() {
        return windowId;
    }

    public void setWindowId(byte windowId) {
        this.windowId = windowId;
    }

    public short getActionNumber() {
        return actionNumber;
    }

    public void setActionNumber(short actionNumber) {
        this.actionNumber = actionNumber;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(packetId));
        packet.writeByte(windowId);
        packet.writeShort(actionNumber);
        packet.writeBoolean(accepted);
        return packet.toByteArray();
    }

}
