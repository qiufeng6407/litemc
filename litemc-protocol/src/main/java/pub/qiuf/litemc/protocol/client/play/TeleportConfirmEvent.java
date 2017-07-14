package pub.qiuf.litemc.protocol.client.play;

import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.VarUtils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class TeleportConfirmEvent extends ClientEvent {

    private int teleportId;

    public TeleportConfirmEvent(int teleportId) {
        super(0x00);
        this.teleportId = teleportId;
    }

    public int getTeleportId() {
        return teleportId;
    }

    public void setTeleportId(int teleportId) {
        this.teleportId = teleportId;
    }

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(packetId));
        packet.write(VarUtils.encodeVarInt(teleportId));
        return packet.toByteArray();
    }

}
