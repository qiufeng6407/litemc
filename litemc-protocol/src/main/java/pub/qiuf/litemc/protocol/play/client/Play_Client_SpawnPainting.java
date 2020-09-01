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
@ServerPacket(0x04)
public class Play_Client_SpawnPainting extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x04;
    @MCType("VarInt")
    private int entityId;
    @MCType("UUID")
    private long[] entityUuid = new long[2];
    /** Panting's ID, see below */
    @MCType("VarInt")
    private int motive;
    /** Center coordinates (see below) */
    @MCType("Position")
    private byte[] location = new byte[8];
    /** Direction the painting faces (North = 2, South = 0, West = 1, East = 3) */
    @MCType("Byte")
    private byte direction;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        packet.write(ByteUtils.encodeVarInt(entityId));
        for (long temp : entityUuid) {
            packet.write(ByteUtils.encodeLong(temp));
        }
        // Panting's ID, see below
        packet.write(ByteUtils.encodeVarInt(motive));
        // Center coordinates (see below)
        packet.write(location);
        // Direction the painting faces (North = 2, South = 0, West = 1, East = 3)
        packet.write(ByteUtils.encodeByte(direction));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        entityId = StreamUtils.readVarInt(in);
        // TODO decode entityUuid
        // Panting's ID, see below
        motive = StreamUtils.readVarInt(in);
        // Center coordinates (see below)
        // TODO decode location
        // Direction the painting faces (North = 2, South = 0, West = 1, East = 3)
        direction = StreamUtils.readByte(in);
    }
    public static class Play_Client_SpawnPainting_Builder {
        private Play_Client_SpawnPainting object = new Play_Client_SpawnPainting();
        /** Packet ID */
        public Play_Client_SpawnPainting_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        public Play_Client_SpawnPainting_Builder entityId(int entityId) {
            object.entityId = entityId;
            return this;
        }
        public Play_Client_SpawnPainting_Builder entityUuid(long[] entityUuid) {
            object.entityUuid = entityUuid;
            return this;
        }
        /** Panting's ID, see below */
        public Play_Client_SpawnPainting_Builder motive(int motive) {
            object.motive = motive;
            return this;
        }
        /** Center coordinates (see below) */
        public Play_Client_SpawnPainting_Builder location(byte[] location) {
            object.location = location;
            return this;
        }
        /** Direction the painting faces (North = 2, South = 0, West = 1, East = 3) */
        public Play_Client_SpawnPainting_Builder direction(byte direction) {
            object.direction = direction;
            return this;
        }
        public Play_Client_SpawnPainting build() {
             return object;
        }

    }
    public static Play_Client_SpawnPainting_Builder builder() {
        return new Play_Client_SpawnPainting_Builder();
    }
}