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
@ServerPacket(0x53)
public class Play_Client_StopSound extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x53;
    /** Controls which fields are present. */
    @MCType("Byte")
    private byte flags;
    /** Only if flags is 3 or 1 (bit mask 0x1). See below. If not present, then sounds from all sources are cleared. */
    @MCType("VarInt")
    private int source;
    /** Only if flags is 2 or 3 (bit mask 0x2). A sound effect name, see Named Sound Effect. If not present, then all sounds are cleared. */
    @MCType("Identifier")
    private String sound;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Controls which fields are present.
        packet.write(ByteUtils.encodeByte(flags));
        // Only if flags is 3 or 1 (bit mask 0x1). See below. If not present, then sounds from all sources are cleared.
        packet.write(ByteUtils.encodeVarInt(source));
        // Only if flags is 2 or 3 (bit mask 0x2). A sound effect name, see Named Sound Effect. If not present, then all sounds are cleared.
        packet.write(ByteUtils.encodeIdentifier(sound));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Controls which fields are present.
        flags = StreamUtils.readByte(in);
        // Only if flags is 3 or 1 (bit mask 0x1). See below. If not present, then sounds from all sources are cleared.
        source = StreamUtils.readVarInt(in);
        // Only if flags is 2 or 3 (bit mask 0x2). A sound effect name, see Named Sound Effect. If not present, then all sounds are cleared.
        sound = StreamUtils.readIdentifier(in);
    }
    public static class Play_Client_StopSound_Builder {
        private Play_Client_StopSound object = new Play_Client_StopSound();
        /** Packet ID */
        public Play_Client_StopSound_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Controls which fields are present. */
        public Play_Client_StopSound_Builder flags(byte flags) {
            object.flags = flags;
            return this;
        }
        /** Only if flags is 3 or 1 (bit mask 0x1). See below. If not present, then sounds from all sources are cleared. */
        public Play_Client_StopSound_Builder source(int source) {
            object.source = source;
            return this;
        }
        /** Only if flags is 2 or 3 (bit mask 0x2). A sound effect name, see Named Sound Effect. If not present, then all sounds are cleared. */
        public Play_Client_StopSound_Builder sound(String sound) {
            object.sound = sound;
            return this;
        }
        public Play_Client_StopSound build() {
             return object;
        }

    }
    public static Play_Client_StopSound_Builder builder() {
        return new Play_Client_StopSound_Builder();
    }
}