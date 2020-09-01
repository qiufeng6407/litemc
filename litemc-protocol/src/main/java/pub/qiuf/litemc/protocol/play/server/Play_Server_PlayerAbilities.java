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
public class Play_Server_PlayerAbilities extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x19;
    /** Bit mask. 0x08: damage disabled (god mode), 0x04: can fly, 0x02: is flying, 0x01: is Creative */
    @MCType("Byte")
    private byte flags;
    @MCType("Float")
    private float flyingSpeed;
    @MCType("Float")
    private float walkingSpeed;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Bit mask. 0x08: damage disabled (god mode), 0x04: can fly, 0x02: is flying, 0x01: is Creative
        packet.write(ByteUtils.encodeByte(flags));
        packet.write(ByteUtils.encodeFloat(flyingSpeed));
        packet.write(ByteUtils.encodeFloat(walkingSpeed));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Bit mask. 0x08: damage disabled (god mode), 0x04: can fly, 0x02: is flying, 0x01: is Creative
        flags = StreamUtils.readByte(in);
        flyingSpeed = StreamUtils.readFloat(in);
        walkingSpeed = StreamUtils.readFloat(in);
    }
    public static class Play_Server_PlayerAbilities_Builder {
        private Play_Server_PlayerAbilities object = new Play_Server_PlayerAbilities();
        /** Packet ID */
        public Play_Server_PlayerAbilities_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Bit mask. 0x08: damage disabled (god mode), 0x04: can fly, 0x02: is flying, 0x01: is Creative */
        public Play_Server_PlayerAbilities_Builder flags(byte flags) {
            object.flags = flags;
            return this;
        }
        public Play_Server_PlayerAbilities_Builder flyingSpeed(float flyingSpeed) {
            object.flyingSpeed = flyingSpeed;
            return this;
        }
        public Play_Server_PlayerAbilities_Builder walkingSpeed(float walkingSpeed) {
            object.walkingSpeed = walkingSpeed;
            return this;
        }
        public Play_Server_PlayerAbilities build() {
             return object;
        }

    }
    public static Play_Server_PlayerAbilities_Builder builder() {
        return new Play_Server_PlayerAbilities_Builder();
    }
}