package pub.qiuf.litemc.protocol.play.client;

import pub.qiuf.litemc.common.bean.datatype.Packet;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import pub.qiuf.litemc.common.util.ByteUtils;
import pub.qiuf.litemc.common.annotation.MCType;
import com.google.common.io.ByteArrayDataOutput;
import pub.qiuf.litemc.common.bean.datatype.ModifierData;
import pub.qiuf.litemc.common.annotation.ServerPacket;
import lombok.Data;
import pub.qiuf.litemc.common.util.StreamUtils;

/**
 * Code is generated by <code>pub.qiuf.litemc.generator.ProtocolGenerator</code>, Please don't modify.<br>
 * See more detail from <a>https://wiki.vg/Protocol</a><br>
 */
@Data
@ServerPacket(0x59)
public class Play_Client_EntityProperties extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x59;
    @MCType("VarInt")
    private int entityId;
    /** Number of elements in the following array */
    @MCType("Int")
    private int numberOfProperties;
    @MCType("Play_Client_EntityProperties_Property")
    private Play_Client_EntityProperties_Property[] property;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        packet.write(ByteUtils.encodeVarInt(entityId));
        // Number of elements in the following array
        packet.write(ByteUtils.encodeInt(numberOfProperties));
        for (Play_Client_EntityProperties_Property temp : property) {
            packet.write(temp.encode());
        }
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        entityId = StreamUtils.readVarInt(in);
        // Number of elements in the following array
        numberOfProperties = StreamUtils.readInt(in);
        property = new Play_Client_EntityProperties_Property[numberOfProperties];
        for (int i = 0; i < property.length; i++) {
            property[i] = new Play_Client_EntityProperties_Property();
            property[i].decode(in);
        }
    }
    @Data
    public static class Play_Client_EntityProperties_Property extends Packet {
        /** See below */
        @MCType("String")
        private String key;
        /** See below */
        @MCType("Double")
        private double value;
        /** Number of elements in the following array */
        @MCType("VarInt")
        private int numberOfModifiers;
        /** See Attribute#Modifiers. Modifier Data defined below. */
        @MCType("ModifierData")
        private ModifierData[] modifiers;

        @Override
        public byte[] encode() throws Exception {
            ByteArrayDataOutput packet = ByteStreams.newDataOutput();
            // See below
            packet.write(ByteUtils.encodeString(key));
            // See below
            packet.write(ByteUtils.encodeDouble(value));
            // Number of elements in the following array
            packet.write(ByteUtils.encodeVarInt(numberOfModifiers));
            // See Attribute#Modifiers. Modifier Data defined below.
            for (ModifierData temp : modifiers) {
                packet.write(ByteUtils.encodeModifierData(temp));
            }
            return packet.toByteArray();
        }
        @Override
        public void decode(ByteArrayDataInput in) throws Exception {
            // See below
            key = StreamUtils.readString(in);
            // See below
            value = StreamUtils.readDouble(in);
            // Number of elements in the following array
            numberOfModifiers = StreamUtils.readVarInt(in);
            // See Attribute#Modifiers. Modifier Data defined below.
            modifiers = new ModifierData[numberOfModifiers];
            for (int i = 0; i < modifiers.length; i++) {
                modifiers[i] = StreamUtils.readModifierData(in);
            }
        }
    }
    @FunctionalInterface
    public interface Play_Client_EntityProperties_Property_Provider {
        Play_Client_EntityProperties_Property[] provide();
    }
    public static Play_Client_EntityProperties_Property_Builder propertyBuilder() {
        return new Play_Client_EntityProperties_Property_Builder();
    }
    public static class Play_Client_EntityProperties_Property_Builder {
        private Play_Client_EntityProperties_Property object = new Play_Client_EntityProperties_Property();
        /** See below */
        public Play_Client_EntityProperties_Property_Builder key(String key) {
            object.key = key;
            return this;
        }
        /** See below */
        public Play_Client_EntityProperties_Property_Builder value(double value) {
            object.value = value;
            return this;
        }
        /** Number of elements in the following array */
        public Play_Client_EntityProperties_Property_Builder numberOfModifiers(int numberOfModifiers) {
            object.numberOfModifiers = numberOfModifiers;
            return this;
        }
        /** See Attribute#Modifiers. Modifier Data defined below. */
        public Play_Client_EntityProperties_Property_Builder modifiers(ModifierData[] modifiers) {
            object.modifiers = modifiers;
            return this;
        }
        public Play_Client_EntityProperties_Property build() {
             return object;
        }

    }
    public static class Play_Client_EntityProperties_Builder {
        private Play_Client_EntityProperties object = new Play_Client_EntityProperties();
        /** Packet ID */
        public Play_Client_EntityProperties_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        public Play_Client_EntityProperties_Builder entityId(int entityId) {
            object.entityId = entityId;
            return this;
        }
        /** Number of elements in the following array */
        public Play_Client_EntityProperties_Builder numberOfProperties(int numberOfProperties) {
            object.numberOfProperties = numberOfProperties;
            return this;
        }
        public Play_Client_EntityProperties_Builder property(Play_Client_EntityProperties_Property_Provider provider) {
            object.property = provider.provide();
            return this;
        }
        public Play_Client_EntityProperties build() {
             return object;
        }

    }
    public static Play_Client_EntityProperties_Builder builder() {
        return new Play_Client_EntityProperties_Builder();
    }
}