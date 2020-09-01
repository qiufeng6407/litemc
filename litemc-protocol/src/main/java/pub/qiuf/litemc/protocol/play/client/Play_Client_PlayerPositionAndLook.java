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
@ServerPacket(0x36)
public class Play_Client_PlayerPositionAndLook extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x36;
    /** Absolute or relative position, depending on Flags */
    @MCType("Double")
    private double x;
    /** Absolute or relative position, depending on Flags */
    @MCType("Double")
    private double y;
    /** Absolute or relative position, depending on Flags */
    @MCType("Double")
    private double z;
    /** Absolute or relative rotation on the X axis, in degrees */
    @MCType("Float")
    private float yaw;
    /** Absolute or relative rotation on the Y axis, in degrees */
    @MCType("Float")
    private float pitch;
    /** Bit field, see below */
    @MCType("Byte")
    private byte flags;
    /** Client should confirm this packet with Teleport Confirm containing the same Teleport ID */
    @MCType("VarInt")
    private int teleportId;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Absolute or relative position, depending on Flags
        packet.write(ByteUtils.encodeDouble(x));
        // Absolute or relative position, depending on Flags
        packet.write(ByteUtils.encodeDouble(y));
        // Absolute or relative position, depending on Flags
        packet.write(ByteUtils.encodeDouble(z));
        // Absolute or relative rotation on the X axis, in degrees
        packet.write(ByteUtils.encodeFloat(yaw));
        // Absolute or relative rotation on the Y axis, in degrees
        packet.write(ByteUtils.encodeFloat(pitch));
        // Bit field, see below
        packet.write(ByteUtils.encodeByte(flags));
        // Client should confirm this packet with Teleport Confirm containing the same Teleport ID
        packet.write(ByteUtils.encodeVarInt(teleportId));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Absolute or relative position, depending on Flags
        x = StreamUtils.readDouble(in);
        // Absolute or relative position, depending on Flags
        y = StreamUtils.readDouble(in);
        // Absolute or relative position, depending on Flags
        z = StreamUtils.readDouble(in);
        // Absolute or relative rotation on the X axis, in degrees
        yaw = StreamUtils.readFloat(in);
        // Absolute or relative rotation on the Y axis, in degrees
        pitch = StreamUtils.readFloat(in);
        // Bit field, see below
        flags = StreamUtils.readByte(in);
        // Client should confirm this packet with Teleport Confirm containing the same Teleport ID
        teleportId = StreamUtils.readVarInt(in);
    }
    public static class Play_Client_PlayerPositionAndLook_Builder {
        private Play_Client_PlayerPositionAndLook object = new Play_Client_PlayerPositionAndLook();
        /** Packet ID */
        public Play_Client_PlayerPositionAndLook_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Absolute or relative position, depending on Flags */
        public Play_Client_PlayerPositionAndLook_Builder x(double x) {
            object.x = x;
            return this;
        }
        /** Absolute or relative position, depending on Flags */
        public Play_Client_PlayerPositionAndLook_Builder y(double y) {
            object.y = y;
            return this;
        }
        /** Absolute or relative position, depending on Flags */
        public Play_Client_PlayerPositionAndLook_Builder z(double z) {
            object.z = z;
            return this;
        }
        /** Absolute or relative rotation on the X axis, in degrees */
        public Play_Client_PlayerPositionAndLook_Builder yaw(float yaw) {
            object.yaw = yaw;
            return this;
        }
        /** Absolute or relative rotation on the Y axis, in degrees */
        public Play_Client_PlayerPositionAndLook_Builder pitch(float pitch) {
            object.pitch = pitch;
            return this;
        }
        /** Bit field, see below */
        public Play_Client_PlayerPositionAndLook_Builder flags(byte flags) {
            object.flags = flags;
            return this;
        }
        /** Client should confirm this packet with Teleport Confirm containing the same Teleport ID */
        public Play_Client_PlayerPositionAndLook_Builder teleportId(int teleportId) {
            object.teleportId = teleportId;
            return this;
        }
        public Play_Client_PlayerPositionAndLook build() {
             return object;
        }

    }
    public static Play_Client_PlayerPositionAndLook_Builder builder() {
        return new Play_Client_PlayerPositionAndLook_Builder();
    }
}