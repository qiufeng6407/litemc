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
@ServerPacket(0x03)
public class Play_Client_SpawnLivingEntity extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x03;
    @MCType("VarInt")
    private int entityId;
    @MCType("UUID")
    private long[] entityUuid = new long[2];
    /** The type of mob. See Entities#Mobs */
    @MCType("VarInt")
    private int type;
    @MCType("Double")
    private double x;
    @MCType("Double")
    private double y;
    @MCType("Double")
    private double z;
    @MCType("Angle")
    private byte yaw;
    @MCType("Angle")
    private byte pitch;
    @MCType("Angle")
    private byte headPitch;
    /** Same units as Entity Velocity */
    @MCType("Short")
    private short velocityX;
    /** Same units as Entity Velocity */
    @MCType("Short")
    private short velocityY;
    /** Same units as Entity Velocity */
    @MCType("Short")
    private short velocityZ;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        packet.write(ByteUtils.encodeVarInt(entityId));
        for (long temp : entityUuid) {
            packet.write(ByteUtils.encodeLong(temp));
        }
        // The type of mob. See Entities#Mobs
        packet.write(ByteUtils.encodeVarInt(type));
        packet.write(ByteUtils.encodeDouble(x));
        packet.write(ByteUtils.encodeDouble(y));
        packet.write(ByteUtils.encodeDouble(z));
        packet.write(ByteUtils.encodeAngle(yaw));
        packet.write(ByteUtils.encodeAngle(pitch));
        packet.write(ByteUtils.encodeAngle(headPitch));
        // Same units as Entity Velocity
        packet.write(ByteUtils.encodeShort(velocityX));
        // Same units as Entity Velocity
        packet.write(ByteUtils.encodeShort(velocityY));
        // Same units as Entity Velocity
        packet.write(ByteUtils.encodeShort(velocityZ));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        entityId = StreamUtils.readVarInt(in);
        // TODO decode entityUuid
        // The type of mob. See Entities#Mobs
        type = StreamUtils.readVarInt(in);
        x = StreamUtils.readDouble(in);
        y = StreamUtils.readDouble(in);
        z = StreamUtils.readDouble(in);
        yaw = StreamUtils.readAngle(in);
        pitch = StreamUtils.readAngle(in);
        headPitch = StreamUtils.readAngle(in);
        // Same units as Entity Velocity
        velocityX = StreamUtils.readShort(in);
        // Same units as Entity Velocity
        velocityY = StreamUtils.readShort(in);
        // Same units as Entity Velocity
        velocityZ = StreamUtils.readShort(in);
    }
    public static class Play_Client_SpawnLivingEntity_Builder {
        private Play_Client_SpawnLivingEntity object = new Play_Client_SpawnLivingEntity();
        /** Packet ID */
        public Play_Client_SpawnLivingEntity_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        public Play_Client_SpawnLivingEntity_Builder entityId(int entityId) {
            object.entityId = entityId;
            return this;
        }
        public Play_Client_SpawnLivingEntity_Builder entityUuid(long[] entityUuid) {
            object.entityUuid = entityUuid;
            return this;
        }
        /** The type of mob. See Entities#Mobs */
        public Play_Client_SpawnLivingEntity_Builder type(int type) {
            object.type = type;
            return this;
        }
        public Play_Client_SpawnLivingEntity_Builder x(double x) {
            object.x = x;
            return this;
        }
        public Play_Client_SpawnLivingEntity_Builder y(double y) {
            object.y = y;
            return this;
        }
        public Play_Client_SpawnLivingEntity_Builder z(double z) {
            object.z = z;
            return this;
        }
        public Play_Client_SpawnLivingEntity_Builder yaw(byte yaw) {
            object.yaw = yaw;
            return this;
        }
        public Play_Client_SpawnLivingEntity_Builder pitch(byte pitch) {
            object.pitch = pitch;
            return this;
        }
        public Play_Client_SpawnLivingEntity_Builder headPitch(byte headPitch) {
            object.headPitch = headPitch;
            return this;
        }
        /** Same units as Entity Velocity */
        public Play_Client_SpawnLivingEntity_Builder velocityX(short velocityX) {
            object.velocityX = velocityX;
            return this;
        }
        /** Same units as Entity Velocity */
        public Play_Client_SpawnLivingEntity_Builder velocityY(short velocityY) {
            object.velocityY = velocityY;
            return this;
        }
        /** Same units as Entity Velocity */
        public Play_Client_SpawnLivingEntity_Builder velocityZ(short velocityZ) {
            object.velocityZ = velocityZ;
            return this;
        }
        public Play_Client_SpawnLivingEntity build() {
             return object;
        }

    }
    public static Play_Client_SpawnLivingEntity_Builder builder() {
        return new Play_Client_SpawnLivingEntity_Builder();
    }
}