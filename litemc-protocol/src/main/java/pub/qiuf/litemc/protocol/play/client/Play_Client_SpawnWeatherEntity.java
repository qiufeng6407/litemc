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
@ServerPacket(0x02)
public class Play_Client_SpawnWeatherEntity extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x02;
    /** The EID of the thunderbolt */
    @MCType("VarInt")
    private int entityId;
    /** The global entity type, currently always 1 for thunderbolt */
    @MCType("Byte")
    private byte type;
    @MCType("Double")
    private double x;
    @MCType("Double")
    private double y;
    @MCType("Double")
    private double z;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // The EID of the thunderbolt
        packet.write(ByteUtils.encodeVarInt(entityId));
        // The global entity type, currently always 1 for thunderbolt
        packet.write(ByteUtils.encodeByte(type));
        packet.write(ByteUtils.encodeDouble(x));
        packet.write(ByteUtils.encodeDouble(y));
        packet.write(ByteUtils.encodeDouble(z));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // The EID of the thunderbolt
        entityId = StreamUtils.readVarInt(in);
        // The global entity type, currently always 1 for thunderbolt
        type = StreamUtils.readByte(in);
        x = StreamUtils.readDouble(in);
        y = StreamUtils.readDouble(in);
        z = StreamUtils.readDouble(in);
    }
    public static class Play_Client_SpawnWeatherEntity_Builder {
        private Play_Client_SpawnWeatherEntity object = new Play_Client_SpawnWeatherEntity();
        /** Packet ID */
        public Play_Client_SpawnWeatherEntity_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** The EID of the thunderbolt */
        public Play_Client_SpawnWeatherEntity_Builder entityId(int entityId) {
            object.entityId = entityId;
            return this;
        }
        /** The global entity type, currently always 1 for thunderbolt */
        public Play_Client_SpawnWeatherEntity_Builder type(byte type) {
            object.type = type;
            return this;
        }
        public Play_Client_SpawnWeatherEntity_Builder x(double x) {
            object.x = x;
            return this;
        }
        public Play_Client_SpawnWeatherEntity_Builder y(double y) {
            object.y = y;
            return this;
        }
        public Play_Client_SpawnWeatherEntity_Builder z(double z) {
            object.z = z;
            return this;
        }
        public Play_Client_SpawnWeatherEntity build() {
             return object;
        }

    }
    public static Play_Client_SpawnWeatherEntity_Builder builder() {
        return new Play_Client_SpawnWeatherEntity_Builder();
    }
}