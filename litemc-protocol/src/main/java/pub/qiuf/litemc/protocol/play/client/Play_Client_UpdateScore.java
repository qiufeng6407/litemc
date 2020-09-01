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
@ServerPacket(0x4D)
public class Play_Client_UpdateScore extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x4D;
    /** The entity whose score this is. For players, this is their username; for other entities, it is their UUID. */
    @MCType("String")
    private String entityName;
    /** 0 to create/update an item. 1 to remove an item. */
    @MCType("Byte")
    private byte action;
    /** The name of the objective the score belongs to */
    @MCType("String")
    private String objectiveName;
    /** The score to be displayed next to the entry. Only sent when Action does not equal 1. */
    @MCType("VarInt")
    private int value;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // The entity whose score this is. For players, this is their username; for other entities, it is their UUID.
        packet.write(ByteUtils.encodeString(entityName));
        // 0 to create/update an item. 1 to remove an item.
        packet.write(ByteUtils.encodeByte(action));
        // The name of the objective the score belongs to
        packet.write(ByteUtils.encodeString(objectiveName));
        // The score to be displayed next to the entry. Only sent when Action does not equal 1.
        packet.write(ByteUtils.encodeVarInt(value));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // The entity whose score this is. For players, this is their username; for other entities, it is their UUID.
        entityName = StreamUtils.readString(in);
        // 0 to create/update an item. 1 to remove an item.
        action = StreamUtils.readByte(in);
        // The name of the objective the score belongs to
        objectiveName = StreamUtils.readString(in);
        // The score to be displayed next to the entry. Only sent when Action does not equal 1.
        value = StreamUtils.readVarInt(in);
    }
    public static class Play_Client_UpdateScore_Builder {
        private Play_Client_UpdateScore object = new Play_Client_UpdateScore();
        /** Packet ID */
        public Play_Client_UpdateScore_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** The entity whose score this is. For players, this is their username; for other entities, it is their UUID. */
        public Play_Client_UpdateScore_Builder entityName(String entityName) {
            object.entityName = entityName;
            return this;
        }
        /** 0 to create/update an item. 1 to remove an item. */
        public Play_Client_UpdateScore_Builder action(byte action) {
            object.action = action;
            return this;
        }
        /** The name of the objective the score belongs to */
        public Play_Client_UpdateScore_Builder objectiveName(String objectiveName) {
            object.objectiveName = objectiveName;
            return this;
        }
        /** The score to be displayed next to the entry. Only sent when Action does not equal 1. */
        public Play_Client_UpdateScore_Builder value(int value) {
            object.value = value;
            return this;
        }
        public Play_Client_UpdateScore build() {
             return object;
        }

    }
    public static Play_Client_UpdateScore_Builder builder() {
        return new Play_Client_UpdateScore_Builder();
    }
}