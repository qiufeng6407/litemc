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
@ServerPacket(0x3E)
public class Play_Client_WorldBorder extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x3E;
    /** Determines the format of the rest of the packet */
    @MCType("VarInt")
    private int action;
    /** Length of a single side of the world border, in meters */
    @MCType("Double")
    private double diameter;
    @MCType("Play_Client_WorldBorder_LerpSize")
    private Play_Client_WorldBorder_LerpSize lerpSize;
    @MCType("Play_Client_WorldBorder_SetCenter")
    private Play_Client_WorldBorder_SetCenter setCenter;
    @MCType("Play_Client_WorldBorder_Initialize")
    private Play_Client_WorldBorder_Initialize initialize;
    /** In seconds as set by /worldborder warning time */
    @MCType("VarInt")
    private int warningTime;
    /** In meters */
    @MCType("VarInt")
    private int warningBlocks;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Determines the format of the rest of the packet
        packet.write(ByteUtils.encodeVarInt(action));
        // Length of a single side of the world border, in meters
        packet.write(ByteUtils.encodeDouble(diameter));
        packet.write(lerpSize.encode());
        packet.write(setCenter.encode());
        packet.write(initialize.encode());
        // In seconds as set by /worldborder warning time
        packet.write(ByteUtils.encodeVarInt(warningTime));
        // In meters
        packet.write(ByteUtils.encodeVarInt(warningBlocks));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Determines the format of the rest of the packet
        action = StreamUtils.readVarInt(in);
        // Length of a single side of the world border, in meters
        diameter = StreamUtils.readDouble(in);
        lerpSize = new Play_Client_WorldBorder_LerpSize();
        lerpSize.decode(in);
        setCenter = new Play_Client_WorldBorder_SetCenter();
        setCenter.decode(in);
        initialize = new Play_Client_WorldBorder_Initialize();
        initialize.decode(in);
        // In seconds as set by /worldborder warning time
        warningTime = StreamUtils.readVarInt(in);
        // In meters
        warningBlocks = StreamUtils.readVarInt(in);
    }
    @Data
    public static class Play_Client_WorldBorder_LerpSize extends Packet {
        /** Current length of a single side of the world border, in meters */
        @MCType("Double")
        private double oldDiameter;
        /** Target length of a single side of the world border, in meters */
        @MCType("Double")
        private double newDiameter;
        /** Number of real-time milliseconds until New Diameter is reached. It appears that Notchian server does not sync world border speed to game ticks, so it gets out of sync with server lag. If the world border is not moving, this is set to 0. */
        @MCType("VarLong")
        private long speed;

        @Override
        public byte[] encode() throws Exception {
            ByteArrayDataOutput packet = ByteStreams.newDataOutput();
            // Current length of a single side of the world border, in meters
            packet.write(ByteUtils.encodeDouble(oldDiameter));
            // Target length of a single side of the world border, in meters
            packet.write(ByteUtils.encodeDouble(newDiameter));
            // Number of real-time milliseconds until New Diameter is reached. It appears that Notchian server does not sync world border speed to game ticks, so it gets out of sync with server lag. If the world border is not moving, this is set to 0.
            packet.write(ByteUtils.encodeVarLong(speed));
            return packet.toByteArray();
        }
        @Override
        public void decode(ByteArrayDataInput in) throws Exception {
            // Current length of a single side of the world border, in meters
            oldDiameter = StreamUtils.readDouble(in);
            // Target length of a single side of the world border, in meters
            newDiameter = StreamUtils.readDouble(in);
            // Number of real-time milliseconds until New Diameter is reached. It appears that Notchian server does not sync world border speed to game ticks, so it gets out of sync with server lag. If the world border is not moving, this is set to 0.
            speed = StreamUtils.readVarLong(in);
        }
    }
    @FunctionalInterface
    public interface Play_Client_WorldBorder_LerpSize_Provider {
        Play_Client_WorldBorder_LerpSize provide();
    }
    public static Play_Client_WorldBorder_LerpSize_Builder lerpSizeBuilder() {
        return new Play_Client_WorldBorder_LerpSize_Builder();
    }
    public static class Play_Client_WorldBorder_LerpSize_Builder {
        private Play_Client_WorldBorder_LerpSize object = new Play_Client_WorldBorder_LerpSize();
        /** Current length of a single side of the world border, in meters */
        public Play_Client_WorldBorder_LerpSize_Builder oldDiameter(double oldDiameter) {
            object.oldDiameter = oldDiameter;
            return this;
        }
        /** Target length of a single side of the world border, in meters */
        public Play_Client_WorldBorder_LerpSize_Builder newDiameter(double newDiameter) {
            object.newDiameter = newDiameter;
            return this;
        }
        /** Number of real-time milliseconds until New Diameter is reached. It appears that Notchian server does not sync world border speed to game ticks, so it gets out of sync with server lag. If the world border is not moving, this is set to 0. */
        public Play_Client_WorldBorder_LerpSize_Builder speed(long speed) {
            object.speed = speed;
            return this;
        }
        public Play_Client_WorldBorder_LerpSize build() {
             return object;
        }

    }
    @Data
    public static class Play_Client_WorldBorder_SetCenter extends Packet {
        @MCType("Double")
        private double x;
        @MCType("Double")
        private double z;

        @Override
        public byte[] encode() throws Exception {
            ByteArrayDataOutput packet = ByteStreams.newDataOutput();
            packet.write(ByteUtils.encodeDouble(x));
            packet.write(ByteUtils.encodeDouble(z));
            return packet.toByteArray();
        }
        @Override
        public void decode(ByteArrayDataInput in) throws Exception {
            x = StreamUtils.readDouble(in);
            z = StreamUtils.readDouble(in);
        }
    }
    @FunctionalInterface
    public interface Play_Client_WorldBorder_SetCenter_Provider {
        Play_Client_WorldBorder_SetCenter provide();
    }
    public static Play_Client_WorldBorder_SetCenter_Builder setCenterBuilder() {
        return new Play_Client_WorldBorder_SetCenter_Builder();
    }
    public static class Play_Client_WorldBorder_SetCenter_Builder {
        private Play_Client_WorldBorder_SetCenter object = new Play_Client_WorldBorder_SetCenter();
        public Play_Client_WorldBorder_SetCenter_Builder x(double x) {
            object.x = x;
            return this;
        }
        public Play_Client_WorldBorder_SetCenter_Builder z(double z) {
            object.z = z;
            return this;
        }
        public Play_Client_WorldBorder_SetCenter build() {
             return object;
        }

    }
    @Data
    public static class Play_Client_WorldBorder_Initialize extends Packet {
        @MCType("Double")
        private double x;
        @MCType("Double")
        private double z;
        /** Current length of a single side of the world border, in meters */
        @MCType("Double")
        private double oldDiameter;
        /** Target length of a single side of the world border, in meters */
        @MCType("Double")
        private double newDiameter;
        /** Number of real-time milliseconds until New Diameter is reached. It appears that Notchian server does not sync world border speed to game ticks, so it gets out of sync with server lag. If the world border is not moving, this is set to 0. */
        @MCType("VarLong")
        private long speed;
        /** Resulting coordinates from a portal teleport are limited to ±value. Usually 29999984. */
        @MCType("VarInt")
        private int portalTeleportBoundary;
        /** In seconds as set by /worldborder warning time */
        @MCType("VarInt")
        private int warningTime;
        /** In meters */
        @MCType("VarInt")
        private int warningBlocks;

        @Override
        public byte[] encode() throws Exception {
            ByteArrayDataOutput packet = ByteStreams.newDataOutput();
            packet.write(ByteUtils.encodeDouble(x));
            packet.write(ByteUtils.encodeDouble(z));
            // Current length of a single side of the world border, in meters
            packet.write(ByteUtils.encodeDouble(oldDiameter));
            // Target length of a single side of the world border, in meters
            packet.write(ByteUtils.encodeDouble(newDiameter));
            // Number of real-time milliseconds until New Diameter is reached. It appears that Notchian server does not sync world border speed to game ticks, so it gets out of sync with server lag. If the world border is not moving, this is set to 0.
            packet.write(ByteUtils.encodeVarLong(speed));
            // Resulting coordinates from a portal teleport are limited to ±value. Usually 29999984.
            packet.write(ByteUtils.encodeVarInt(portalTeleportBoundary));
            // In seconds as set by /worldborder warning time
            packet.write(ByteUtils.encodeVarInt(warningTime));
            // In meters
            packet.write(ByteUtils.encodeVarInt(warningBlocks));
            return packet.toByteArray();
        }
        @Override
        public void decode(ByteArrayDataInput in) throws Exception {
            x = StreamUtils.readDouble(in);
            z = StreamUtils.readDouble(in);
            // Current length of a single side of the world border, in meters
            oldDiameter = StreamUtils.readDouble(in);
            // Target length of a single side of the world border, in meters
            newDiameter = StreamUtils.readDouble(in);
            // Number of real-time milliseconds until New Diameter is reached. It appears that Notchian server does not sync world border speed to game ticks, so it gets out of sync with server lag. If the world border is not moving, this is set to 0.
            speed = StreamUtils.readVarLong(in);
            // Resulting coordinates from a portal teleport are limited to ±value. Usually 29999984.
            portalTeleportBoundary = StreamUtils.readVarInt(in);
            // In seconds as set by /worldborder warning time
            warningTime = StreamUtils.readVarInt(in);
            // In meters
            warningBlocks = StreamUtils.readVarInt(in);
        }
    }
    @FunctionalInterface
    public interface Play_Client_WorldBorder_Initialize_Provider {
        Play_Client_WorldBorder_Initialize provide();
    }
    public static Play_Client_WorldBorder_Initialize_Builder initializeBuilder() {
        return new Play_Client_WorldBorder_Initialize_Builder();
    }
    public static class Play_Client_WorldBorder_Initialize_Builder {
        private Play_Client_WorldBorder_Initialize object = new Play_Client_WorldBorder_Initialize();
        public Play_Client_WorldBorder_Initialize_Builder x(double x) {
            object.x = x;
            return this;
        }
        public Play_Client_WorldBorder_Initialize_Builder z(double z) {
            object.z = z;
            return this;
        }
        /** Current length of a single side of the world border, in meters */
        public Play_Client_WorldBorder_Initialize_Builder oldDiameter(double oldDiameter) {
            object.oldDiameter = oldDiameter;
            return this;
        }
        /** Target length of a single side of the world border, in meters */
        public Play_Client_WorldBorder_Initialize_Builder newDiameter(double newDiameter) {
            object.newDiameter = newDiameter;
            return this;
        }
        /** Number of real-time milliseconds until New Diameter is reached. It appears that Notchian server does not sync world border speed to game ticks, so it gets out of sync with server lag. If the world border is not moving, this is set to 0. */
        public Play_Client_WorldBorder_Initialize_Builder speed(long speed) {
            object.speed = speed;
            return this;
        }
        /** Resulting coordinates from a portal teleport are limited to ±value. Usually 29999984. */
        public Play_Client_WorldBorder_Initialize_Builder portalTeleportBoundary(int portalTeleportBoundary) {
            object.portalTeleportBoundary = portalTeleportBoundary;
            return this;
        }
        /** In seconds as set by /worldborder warning time */
        public Play_Client_WorldBorder_Initialize_Builder warningTime(int warningTime) {
            object.warningTime = warningTime;
            return this;
        }
        /** In meters */
        public Play_Client_WorldBorder_Initialize_Builder warningBlocks(int warningBlocks) {
            object.warningBlocks = warningBlocks;
            return this;
        }
        public Play_Client_WorldBorder_Initialize build() {
             return object;
        }

    }
    public static class Play_Client_WorldBorder_Builder {
        private Play_Client_WorldBorder object = new Play_Client_WorldBorder();
        /** Packet ID */
        public Play_Client_WorldBorder_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Determines the format of the rest of the packet */
        public Play_Client_WorldBorder_Builder action(int action) {
            object.action = action;
            return this;
        }
        /** Length of a single side of the world border, in meters */
        public Play_Client_WorldBorder_Builder diameter(double diameter) {
            object.diameter = diameter;
            return this;
        }
        public Play_Client_WorldBorder_Builder lerpSize(Play_Client_WorldBorder_LerpSize_Provider provider) {
            object.lerpSize = provider.provide();
            return this;
        }
        public Play_Client_WorldBorder_Builder setCenter(Play_Client_WorldBorder_SetCenter_Provider provider) {
            object.setCenter = provider.provide();
            return this;
        }
        public Play_Client_WorldBorder_Builder initialize(Play_Client_WorldBorder_Initialize_Provider provider) {
            object.initialize = provider.provide();
            return this;
        }
        /** In seconds as set by /worldborder warning time */
        public Play_Client_WorldBorder_Builder warningTime(int warningTime) {
            object.warningTime = warningTime;
            return this;
        }
        /** In meters */
        public Play_Client_WorldBorder_Builder warningBlocks(int warningBlocks) {
            object.warningBlocks = warningBlocks;
            return this;
        }
        public Play_Client_WorldBorder build() {
             return object;
        }

    }
    public static Play_Client_WorldBorder_Builder builder() {
        return new Play_Client_WorldBorder_Builder();
    }
}