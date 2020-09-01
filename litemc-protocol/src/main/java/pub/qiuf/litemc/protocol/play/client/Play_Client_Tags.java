package pub.qiuf.litemc.protocol.play.client;

import pub.qiuf.litemc.common.bean.datatype.Packet;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import pub.qiuf.litemc.common.util.ByteUtils;
import pub.qiuf.litemc.common.annotation.MCType;
import com.google.common.io.ByteArrayDataOutput;
import pub.qiuf.litemc.common.annotation.ServerPacket;
import lombok.Data;
import pub.qiuf.litemc.common.bean.datatype.Tags;
import pub.qiuf.litemc.common.util.StreamUtils;

/**
 * Code is generated by <code>pub.qiuf.litemc.generator.ProtocolGenerator</code>, Please don't modify.<br>
 * See more detail from <a>https://wiki.vg/Protocol</a><br>
 */
@Data
@ServerPacket(0x5C)
public class Play_Client_Tags extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x5C;
    /** IDs are block IDs */
    @MCType("Tags")
    private Tags blockTags;
    /** IDs are item IDs */
    @MCType("Tags")
    private Tags itemTags;
    /** IDs are fluid IDs */
    @MCType("Tags")
    private Tags fluidTags;
    /** IDs are entity IDs */
    @MCType("Tags")
    private Tags entityTags;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // IDs are block IDs
        packet.write(ByteUtils.encodeTags(blockTags));
        // IDs are item IDs
        packet.write(ByteUtils.encodeTags(itemTags));
        // IDs are fluid IDs
        packet.write(ByteUtils.encodeTags(fluidTags));
        // IDs are entity IDs
        packet.write(ByteUtils.encodeTags(entityTags));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // IDs are block IDs
        blockTags = StreamUtils.readTags(in);
        // IDs are item IDs
        itemTags = StreamUtils.readTags(in);
        // IDs are fluid IDs
        fluidTags = StreamUtils.readTags(in);
        // IDs are entity IDs
        entityTags = StreamUtils.readTags(in);
    }
    public static class Play_Client_Tags_Builder {
        private Play_Client_Tags object = new Play_Client_Tags();
        /** Packet ID */
        public Play_Client_Tags_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** IDs are block IDs */
        public Play_Client_Tags_Builder blockTags(Tags blockTags) {
            object.blockTags = blockTags;
            return this;
        }
        /** IDs are item IDs */
        public Play_Client_Tags_Builder itemTags(Tags itemTags) {
            object.itemTags = itemTags;
            return this;
        }
        /** IDs are fluid IDs */
        public Play_Client_Tags_Builder fluidTags(Tags fluidTags) {
            object.fluidTags = fluidTags;
            return this;
        }
        /** IDs are entity IDs */
        public Play_Client_Tags_Builder entityTags(Tags entityTags) {
            object.entityTags = entityTags;
            return this;
        }
        public Play_Client_Tags build() {
             return object;
        }

    }
    public static Play_Client_Tags_Builder builder() {
        return new Play_Client_Tags_Builder();
    }
}