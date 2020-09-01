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
@ServerPacket(0x33)
public class Play_Client_CombatEvent extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x33;
    /** Determines the layout of the remaining packet */
    @MCType("VarInt")
    private int event;
    @MCType("Play_Client_CombatEvent_EndCombat")
    private Play_Client_CombatEvent_EndCombat endCombat;
    @MCType("Play_Client_CombatEvent_EntityDead")
    private Play_Client_CombatEvent_EntityDead entityDead;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Determines the layout of the remaining packet
        packet.write(ByteUtils.encodeVarInt(event));
        packet.write(endCombat.encode());
        packet.write(entityDead.encode());
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Determines the layout of the remaining packet
        event = StreamUtils.readVarInt(in);
        endCombat = new Play_Client_CombatEvent_EndCombat();
        endCombat.decode(in);
        entityDead = new Play_Client_CombatEvent_EntityDead();
        entityDead.decode(in);
    }
    @Data
    public static class Play_Client_CombatEvent_EndCombat extends Packet {
        /** Length of the combat in ticks. */
        @MCType("VarInt")
        private int duration;
        /** ID of the primary opponent of the ended combat, or -1 if there is no obvious primary opponent. */
        @MCType("Int")
        private int entityId;

        @Override
        public byte[] encode() throws Exception {
            ByteArrayDataOutput packet = ByteStreams.newDataOutput();
            // Length of the combat in ticks.
            packet.write(ByteUtils.encodeVarInt(duration));
            // ID of the primary opponent of the ended combat, or -1 if there is no obvious primary opponent.
            packet.write(ByteUtils.encodeInt(entityId));
            return packet.toByteArray();
        }
        @Override
        public void decode(ByteArrayDataInput in) throws Exception {
            // Length of the combat in ticks.
            duration = StreamUtils.readVarInt(in);
            // ID of the primary opponent of the ended combat, or -1 if there is no obvious primary opponent.
            entityId = StreamUtils.readInt(in);
        }
    }
    @FunctionalInterface
    public interface Play_Client_CombatEvent_EndCombat_Provider {
        Play_Client_CombatEvent_EndCombat provide();
    }
    public static Play_Client_CombatEvent_EndCombat_Builder endCombatBuilder() {
        return new Play_Client_CombatEvent_EndCombat_Builder();
    }
    public static class Play_Client_CombatEvent_EndCombat_Builder {
        private Play_Client_CombatEvent_EndCombat object = new Play_Client_CombatEvent_EndCombat();
        /** Length of the combat in ticks. */
        public Play_Client_CombatEvent_EndCombat_Builder duration(int duration) {
            object.duration = duration;
            return this;
        }
        /** ID of the primary opponent of the ended combat, or -1 if there is no obvious primary opponent. */
        public Play_Client_CombatEvent_EndCombat_Builder entityId(int entityId) {
            object.entityId = entityId;
            return this;
        }
        public Play_Client_CombatEvent_EndCombat build() {
             return object;
        }

    }
    @Data
    public static class Play_Client_CombatEvent_EntityDead extends Packet {
        /** Entity ID of the player that died (should match the client's entity ID). */
        @MCType("VarInt")
        private int playerId;
        /** The killing entity's ID, or -1 if there is no obvious killer. */
        @MCType("Int")
        private int entityId;
        /** The death message */
        @MCType("Chat")
        private String message;

        @Override
        public byte[] encode() throws Exception {
            ByteArrayDataOutput packet = ByteStreams.newDataOutput();
            // Entity ID of the player that died (should match the client's entity ID).
            packet.write(ByteUtils.encodeVarInt(playerId));
            // The killing entity's ID, or -1 if there is no obvious killer.
            packet.write(ByteUtils.encodeInt(entityId));
            // The death message
            packet.write(ByteUtils.encodeChat(message));
            return packet.toByteArray();
        }
        @Override
        public void decode(ByteArrayDataInput in) throws Exception {
            // Entity ID of the player that died (should match the client's entity ID).
            playerId = StreamUtils.readVarInt(in);
            // The killing entity's ID, or -1 if there is no obvious killer.
            entityId = StreamUtils.readInt(in);
            // The death message
            message = StreamUtils.readChat(in);
        }
    }
    @FunctionalInterface
    public interface Play_Client_CombatEvent_EntityDead_Provider {
        Play_Client_CombatEvent_EntityDead provide();
    }
    public static Play_Client_CombatEvent_EntityDead_Builder entityDeadBuilder() {
        return new Play_Client_CombatEvent_EntityDead_Builder();
    }
    public static class Play_Client_CombatEvent_EntityDead_Builder {
        private Play_Client_CombatEvent_EntityDead object = new Play_Client_CombatEvent_EntityDead();
        /** Entity ID of the player that died (should match the client's entity ID). */
        public Play_Client_CombatEvent_EntityDead_Builder playerId(int playerId) {
            object.playerId = playerId;
            return this;
        }
        /** The killing entity's ID, or -1 if there is no obvious killer. */
        public Play_Client_CombatEvent_EntityDead_Builder entityId(int entityId) {
            object.entityId = entityId;
            return this;
        }
        /** The death message */
        public Play_Client_CombatEvent_EntityDead_Builder message(String message) {
            object.message = message;
            return this;
        }
        public Play_Client_CombatEvent_EntityDead build() {
             return object;
        }

    }
    public static class Play_Client_CombatEvent_Builder {
        private Play_Client_CombatEvent object = new Play_Client_CombatEvent();
        /** Packet ID */
        public Play_Client_CombatEvent_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Determines the layout of the remaining packet */
        public Play_Client_CombatEvent_Builder event(int event) {
            object.event = event;
            return this;
        }
        public Play_Client_CombatEvent_Builder endCombat(Play_Client_CombatEvent_EndCombat_Provider provider) {
            object.endCombat = provider.provide();
            return this;
        }
        public Play_Client_CombatEvent_Builder entityDead(Play_Client_CombatEvent_EntityDead_Provider provider) {
            object.entityDead = provider.provide();
            return this;
        }
        public Play_Client_CombatEvent build() {
             return object;
        }

    }
    public static Play_Client_CombatEvent_Builder builder() {
        return new Play_Client_CombatEvent_Builder();
    }
}