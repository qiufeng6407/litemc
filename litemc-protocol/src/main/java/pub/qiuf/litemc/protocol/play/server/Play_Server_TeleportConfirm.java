package pub.qiuf.litemc.protocol.play.server;

import pub.qiuf.litemc.common.bean.datatype.Packet;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import pub.qiuf.litemc.common.util.ByteUtils;
import pub.qiuf.litemc.common.annotation.MCType;
import com.google.common.io.ByteArrayDataOutput;
import pub.qiuf.litemc.common.annotation.ServerPacket;
import lombok.Data;
import pub.qiuf.litemc.common.util.StreamUtils;

/**
 * Code is generated by <code>pub.qiuf.litemc.generator.ProtocolGenerator</code>, Please don't modify.<br>
 * See more detail from <a>https://wiki.vg/Protocol</a><br>
 */
@Data
public class Play_Server_TeleportConfirm extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x00;
    /** The ID given by the Player Position And Look packet */
    @MCType("VarInt")
    private int teleportId;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // The ID given by the Player Position And Look packet
        packet.write(ByteUtils.encodeVarInt(teleportId));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // The ID given by the Player Position And Look packet
        teleportId = StreamUtils.readVarInt(in);
    }
    public static class Play_Server_TeleportConfirm_Builder {
        private Play_Server_TeleportConfirm object = new Play_Server_TeleportConfirm();
        /** Packet ID */
        public Play_Server_TeleportConfirm_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** The ID given by the Player Position And Look packet */
        public Play_Server_TeleportConfirm_Builder teleportId(int teleportId) {
            object.teleportId = teleportId;
            return this;
        }
        public Play_Server_TeleportConfirm build() {
             return object;
        }

    }
    public static Play_Server_TeleportConfirm_Builder builder() {
        return new Play_Server_TeleportConfirm_Builder();
    }
}