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
public class Play_Server_UpdateSign extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x29;
    /** Block Coordinates */
    @MCType("Position")
    private byte[] location = new byte[8];
    /** First line of text in the sign */
    @MCType("String")
    private String line1;
    /** Second line of text in the sign */
    @MCType("String")
    private String line2;
    /** Third line of text in the sign */
    @MCType("String")
    private String line3;
    /** Fourth line of text in the sign */
    @MCType("String")
    private String line4;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Block Coordinates
        packet.write(location);
        // First line of text in the sign
        packet.write(ByteUtils.encodeString(line1));
        // Second line of text in the sign
        packet.write(ByteUtils.encodeString(line2));
        // Third line of text in the sign
        packet.write(ByteUtils.encodeString(line3));
        // Fourth line of text in the sign
        packet.write(ByteUtils.encodeString(line4));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Block Coordinates
        // TODO decode location
        // First line of text in the sign
        line1 = StreamUtils.readString(in);
        // Second line of text in the sign
        line2 = StreamUtils.readString(in);
        // Third line of text in the sign
        line3 = StreamUtils.readString(in);
        // Fourth line of text in the sign
        line4 = StreamUtils.readString(in);
    }
    public static class Play_Server_UpdateSign_Builder {
        private Play_Server_UpdateSign object = new Play_Server_UpdateSign();
        /** Packet ID */
        public Play_Server_UpdateSign_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Block Coordinates */
        public Play_Server_UpdateSign_Builder location(byte[] location) {
            object.location = location;
            return this;
        }
        /** First line of text in the sign */
        public Play_Server_UpdateSign_Builder line1(String line1) {
            object.line1 = line1;
            return this;
        }
        /** Second line of text in the sign */
        public Play_Server_UpdateSign_Builder line2(String line2) {
            object.line2 = line2;
            return this;
        }
        /** Third line of text in the sign */
        public Play_Server_UpdateSign_Builder line3(String line3) {
            object.line3 = line3;
            return this;
        }
        /** Fourth line of text in the sign */
        public Play_Server_UpdateSign_Builder line4(String line4) {
            object.line4 = line4;
            return this;
        }
        public Play_Server_UpdateSign build() {
             return object;
        }

    }
    public static Play_Server_UpdateSign_Builder builder() {
        return new Play_Server_UpdateSign_Builder();
    }
}