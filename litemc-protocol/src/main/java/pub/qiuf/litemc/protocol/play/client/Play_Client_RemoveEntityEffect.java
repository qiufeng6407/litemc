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
@ServerPacket(0x39)
public class Play_Client_RemoveEntityEffect extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x39;
    @MCType("VarInt")
    private int entityId;
    /** See this table */
    @MCType("Byte")
    private byte effectId;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        packet.write(ByteUtils.encodeVarInt(entityId));
        // See this table
        packet.write(ByteUtils.encodeByte(effectId));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        entityId = StreamUtils.readVarInt(in);
        // See this table
        effectId = StreamUtils.readByte(in);
    }
    public static class Play_Client_RemoveEntityEffect_Builder {
        private Play_Client_RemoveEntityEffect object = new Play_Client_RemoveEntityEffect();
        /** Packet ID */
        public Play_Client_RemoveEntityEffect_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        public Play_Client_RemoveEntityEffect_Builder entityId(int entityId) {
            object.entityId = entityId;
            return this;
        }
        /** See this table */
        public Play_Client_RemoveEntityEffect_Builder effectId(byte effectId) {
            object.effectId = effectId;
            return this;
        }
        public Play_Client_RemoveEntityEffect build() {
             return object;
        }

    }
    public static Play_Client_RemoveEntityEffect_Builder builder() {
        return new Play_Client_RemoveEntityEffect_Builder();
    }
}