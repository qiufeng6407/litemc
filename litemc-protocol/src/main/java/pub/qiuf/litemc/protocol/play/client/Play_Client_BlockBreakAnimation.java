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
@ServerPacket(0x09)
public class Play_Client_BlockBreakAnimation extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x09;
    /** Entity ID of the entity breaking the block */
    @MCType("VarInt")
    private int entityId;
    /** Block Position */
    @MCType("Position")
    private byte[] location = new byte[8];
    /** 0–9 to set it, any other value to remove it */
    @MCType("Byte")
    private byte destroyStage;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Entity ID of the entity breaking the block
        packet.write(ByteUtils.encodeVarInt(entityId));
        // Block Position
        packet.write(location);
        // 0–9 to set it, any other value to remove it
        packet.write(ByteUtils.encodeByte(destroyStage));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Entity ID of the entity breaking the block
        entityId = StreamUtils.readVarInt(in);
        // Block Position
        // TODO decode location
        // 0–9 to set it, any other value to remove it
        destroyStage = StreamUtils.readByte(in);
    }
    public static class Play_Client_BlockBreakAnimation_Builder {
        private Play_Client_BlockBreakAnimation object = new Play_Client_BlockBreakAnimation();
        /** Packet ID */
        public Play_Client_BlockBreakAnimation_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Entity ID of the entity breaking the block */
        public Play_Client_BlockBreakAnimation_Builder entityId(int entityId) {
            object.entityId = entityId;
            return this;
        }
        /** Block Position */
        public Play_Client_BlockBreakAnimation_Builder location(byte[] location) {
            object.location = location;
            return this;
        }
        /** 0–9 to set it, any other value to remove it */
        public Play_Client_BlockBreakAnimation_Builder destroyStage(byte destroyStage) {
            object.destroyStage = destroyStage;
            return this;
        }
        public Play_Client_BlockBreakAnimation build() {
             return object;
        }

    }
    public static Play_Client_BlockBreakAnimation_Builder builder() {
        return new Play_Client_BlockBreakAnimation_Builder();
    }
}