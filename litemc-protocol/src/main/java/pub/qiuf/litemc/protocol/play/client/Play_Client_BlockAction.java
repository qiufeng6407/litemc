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
@ServerPacket(0x0B)
public class Play_Client_BlockAction extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x0B;
    /** Block coordinates */
    @MCType("Position")
    private byte[] location = new byte[8];
    /** Varies depending on block — see Block Actions */
    @MCType("UnsignedByte")
    private int actionId;
    /** Varies depending on block — see Block Actions */
    @MCType("UnsignedByte")
    private int actionParam;
    /** The block type ID for the block. This must match the block at the given coordinates. */
    @MCType("VarInt")
    private int blockType;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Block coordinates
        packet.write(location);
        // Varies depending on block — see Block Actions
        packet.write(ByteUtils.encodeUnsignedByte(actionId));
        // Varies depending on block — see Block Actions
        packet.write(ByteUtils.encodeUnsignedByte(actionParam));
        // The block type ID for the block. This must match the block at the given coordinates.
        packet.write(ByteUtils.encodeVarInt(blockType));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Block coordinates
        // TODO decode location
        // Varies depending on block — see Block Actions
        actionId = StreamUtils.readUnsignedByte(in);
        // Varies depending on block — see Block Actions
        actionParam = StreamUtils.readUnsignedByte(in);
        // The block type ID for the block. This must match the block at the given coordinates.
        blockType = StreamUtils.readVarInt(in);
    }
    public static class Play_Client_BlockAction_Builder {
        private Play_Client_BlockAction object = new Play_Client_BlockAction();
        /** Packet ID */
        public Play_Client_BlockAction_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Block coordinates */
        public Play_Client_BlockAction_Builder location(byte[] location) {
            object.location = location;
            return this;
        }
        /** Varies depending on block — see Block Actions */
        public Play_Client_BlockAction_Builder actionId(int actionId) {
            object.actionId = actionId;
            return this;
        }
        /** Varies depending on block — see Block Actions */
        public Play_Client_BlockAction_Builder actionParam(int actionParam) {
            object.actionParam = actionParam;
            return this;
        }
        /** The block type ID for the block. This must match the block at the given coordinates. */
        public Play_Client_BlockAction_Builder blockType(int blockType) {
            object.blockType = blockType;
            return this;
        }
        public Play_Client_BlockAction build() {
             return object;
        }

    }
    public static Play_Client_BlockAction_Builder builder() {
        return new Play_Client_BlockAction_Builder();
    }
}