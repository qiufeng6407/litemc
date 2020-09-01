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
@ServerPacket(0x3D)
public class Play_Client_SelectAdvancementTab extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x3D;
    /** Indicates if the next field is present */
    @MCType("Boolean")
    private boolean hasId;
    /** See below */
    @MCType("String")
    private String optionalIdentifier;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Indicates if the next field is present
        packet.write(ByteUtils.encodeBoolean(hasId));
        // See below
        packet.write(ByteUtils.encodeString(optionalIdentifier));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Indicates if the next field is present
        hasId = StreamUtils.readBoolean(in);
        // See below
        optionalIdentifier = StreamUtils.readString(in);
    }
    public static class Play_Client_SelectAdvancementTab_Builder {
        private Play_Client_SelectAdvancementTab object = new Play_Client_SelectAdvancementTab();
        /** Packet ID */
        public Play_Client_SelectAdvancementTab_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Indicates if the next field is present */
        public Play_Client_SelectAdvancementTab_Builder hasId(boolean hasId) {
            object.hasId = hasId;
            return this;
        }
        /** See below */
        public Play_Client_SelectAdvancementTab_Builder optionalIdentifier(String optionalIdentifier) {
            object.optionalIdentifier = optionalIdentifier;
            return this;
        }
        public Play_Client_SelectAdvancementTab build() {
             return object;
        }

    }
    public static Play_Client_SelectAdvancementTab_Builder builder() {
        return new Play_Client_SelectAdvancementTab_Builder();
    }
}