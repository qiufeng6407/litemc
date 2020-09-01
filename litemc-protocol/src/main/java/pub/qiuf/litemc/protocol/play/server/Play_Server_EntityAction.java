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
public class Play_Server_EntityAction extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x1B;
    /** Player ID */
    @MCType("VarInt")
    private int entityId;
    /** The ID of the action, see below */
    @MCType("VarInt")
    private int actionId;
    /** Only used by the “start jump with horse” action, in which case it ranges from 0 to 100. In all other cases it is 0. */
    @MCType("VarInt")
    private int jumpBoost;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Player ID
        packet.write(ByteUtils.encodeVarInt(entityId));
        // The ID of the action, see below
        packet.write(ByteUtils.encodeVarInt(actionId));
        // Only used by the “start jump with horse” action, in which case it ranges from 0 to 100. In all other cases it is 0.
        packet.write(ByteUtils.encodeVarInt(jumpBoost));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Player ID
        entityId = StreamUtils.readVarInt(in);
        // The ID of the action, see below
        actionId = StreamUtils.readVarInt(in);
        // Only used by the “start jump with horse” action, in which case it ranges from 0 to 100. In all other cases it is 0.
        jumpBoost = StreamUtils.readVarInt(in);
    }
    public static class Play_Server_EntityAction_Builder {
        private Play_Server_EntityAction object = new Play_Server_EntityAction();
        /** Packet ID */
        public Play_Server_EntityAction_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Player ID */
        public Play_Server_EntityAction_Builder entityId(int entityId) {
            object.entityId = entityId;
            return this;
        }
        /** The ID of the action, see below */
        public Play_Server_EntityAction_Builder actionId(int actionId) {
            object.actionId = actionId;
            return this;
        }
        /** Only used by the “start jump with horse” action, in which case it ranges from 0 to 100. In all other cases it is 0. */
        public Play_Server_EntityAction_Builder jumpBoost(int jumpBoost) {
            object.jumpBoost = jumpBoost;
            return this;
        }
        public Play_Server_EntityAction build() {
             return object;
        }

    }
    public static Play_Server_EntityAction_Builder builder() {
        return new Play_Server_EntityAction_Builder();
    }
}