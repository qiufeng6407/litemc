package pub.qiuf.litemc.protocol.play.client;

import pub.qiuf.litemc.common.bean.datatype.Packet;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import pub.qiuf.litemc.common.util.ByteUtils;
import com.flowpowered.nbt.Tag;
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
@ServerPacket(0x0A)
public class Play_Client_BlockEntityData extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x0A;
    @MCType("Position")
    private byte[] location = new byte[8];
    /** The type of update to perform, see below */
    @MCType("UnsignedByte")
    private int action;
    /** Data to set. May be a TAG_END (0), in which case the block entity at the given location is removed (though this is not required since the client will remove the block entity automatically on chunk unload or block removal) */
    @MCType("Tag")
    private Tag nbtData;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        packet.write(location);
        // The type of update to perform, see below
        packet.write(ByteUtils.encodeUnsignedByte(action));
        // Data to set. May be a TAG_END (0), in which case the block entity at the given location is removed (though this is not required since the client will remove the block entity automatically on chunk unload or block removal)
        packet.write(ByteUtils.encodeTag(nbtData));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // TODO decode location
        // The type of update to perform, see below
        action = StreamUtils.readUnsignedByte(in);
        // Data to set. May be a TAG_END (0), in which case the block entity at the given location is removed (though this is not required since the client will remove the block entity automatically on chunk unload or block removal)
        nbtData = StreamUtils.readTag(in);
    }
    public static class Play_Client_BlockEntityData_Builder {
        private Play_Client_BlockEntityData object = new Play_Client_BlockEntityData();
        /** Packet ID */
        public Play_Client_BlockEntityData_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        public Play_Client_BlockEntityData_Builder location(byte[] location) {
            object.location = location;
            return this;
        }
        /** The type of update to perform, see below */
        public Play_Client_BlockEntityData_Builder action(int action) {
            object.action = action;
            return this;
        }
        /** Data to set. May be a TAG_END (0), in which case the block entity at the given location is removed (though this is not required since the client will remove the block entity automatically on chunk unload or block removal) */
        public Play_Client_BlockEntityData_Builder nbtData(Tag nbtData) {
            object.nbtData = nbtData;
            return this;
        }
        public Play_Client_BlockEntityData build() {
             return object;
        }

    }
    public static Play_Client_BlockEntityData_Builder builder() {
        return new Play_Client_BlockEntityData_Builder();
    }
}