package pub.qiuf.litemc.protocol.play.client;

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
@ServerPacket(0x3F)
public class Play_Client_Camera extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x3F;
    /** ID of the entity to set the client's camera to */
    @MCType("VarInt")
    private int cameraId;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // ID of the entity to set the client's camera to
        packet.write(ByteUtils.encodeVarInt(cameraId));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // ID of the entity to set the client's camera to
        cameraId = StreamUtils.readVarInt(in);
    }
    public static class Play_Client_Camera_Builder {
        private Play_Client_Camera object = new Play_Client_Camera();
        /** Packet ID */
        public Play_Client_Camera_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** ID of the entity to set the client's camera to */
        public Play_Client_Camera_Builder cameraId(int cameraId) {
            object.cameraId = cameraId;
            return this;
        }
        public Play_Client_Camera build() {
             return object;
        }

    }
    public static Play_Client_Camera_Builder builder() {
        return new Play_Client_Camera_Builder();
    }
}