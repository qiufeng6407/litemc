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
public class Play_Server_InteractEntity extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x0E;
    /** The ID of the entity to interact */
    @MCType("VarInt")
    private int entityId;
    /** 0: interact, 1: attack, 2: interact at */
    @MCType("VarInt")
    private int type;
    /** Only if Type is interact at */
    @MCType("Float")
    private float targetX;
    /** Only if Type is interact at */
    @MCType("Float")
    private float targetY;
    /** Only if Type is interact at */
    @MCType("Float")
    private float targetZ;
    /** Only if Type is interact or interact at; 0: main hand, 1: off hand */
    @MCType("VarInt")
    private int hand;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // The ID of the entity to interact
        packet.write(ByteUtils.encodeVarInt(entityId));
        // 0: interact, 1: attack, 2: interact at
        packet.write(ByteUtils.encodeVarInt(type));
        // Only if Type is interact at
        packet.write(ByteUtils.encodeFloat(targetX));
        // Only if Type is interact at
        packet.write(ByteUtils.encodeFloat(targetY));
        // Only if Type is interact at
        packet.write(ByteUtils.encodeFloat(targetZ));
        // Only if Type is interact or interact at; 0: main hand, 1: off hand
        packet.write(ByteUtils.encodeVarInt(hand));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // The ID of the entity to interact
        entityId = StreamUtils.readVarInt(in);
        // 0: interact, 1: attack, 2: interact at
        type = StreamUtils.readVarInt(in);
        // Only if Type is interact at
        targetX = StreamUtils.readFloat(in);
        // Only if Type is interact at
        targetY = StreamUtils.readFloat(in);
        // Only if Type is interact at
        targetZ = StreamUtils.readFloat(in);
        // Only if Type is interact or interact at; 0: main hand, 1: off hand
        hand = StreamUtils.readVarInt(in);
    }
    public static class Play_Server_InteractEntity_Builder {
        private Play_Server_InteractEntity object = new Play_Server_InteractEntity();
        /** Packet ID */
        public Play_Server_InteractEntity_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** The ID of the entity to interact */
        public Play_Server_InteractEntity_Builder entityId(int entityId) {
            object.entityId = entityId;
            return this;
        }
        /** 0: interact, 1: attack, 2: interact at */
        public Play_Server_InteractEntity_Builder type(int type) {
            object.type = type;
            return this;
        }
        /** Only if Type is interact at */
        public Play_Server_InteractEntity_Builder targetX(float targetX) {
            object.targetX = targetX;
            return this;
        }
        /** Only if Type is interact at */
        public Play_Server_InteractEntity_Builder targetY(float targetY) {
            object.targetY = targetY;
            return this;
        }
        /** Only if Type is interact at */
        public Play_Server_InteractEntity_Builder targetZ(float targetZ) {
            object.targetZ = targetZ;
            return this;
        }
        /** Only if Type is interact or interact at; 0: main hand, 1: off hand */
        public Play_Server_InteractEntity_Builder hand(int hand) {
            object.hand = hand;
            return this;
        }
        public Play_Server_InteractEntity build() {
             return object;
        }

    }
    public static Play_Server_InteractEntity_Builder builder() {
        return new Play_Server_InteractEntity_Builder();
    }
}