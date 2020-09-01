package pub.qiuf.litemc.protocol.play.server;

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
public class Play_Server_NameItem extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x1E;
    /** The new name of the item */
    @MCType("String")
    private String itemName;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // The new name of the item
        packet.write(ByteUtils.encodeString(itemName));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // The new name of the item
        itemName = StreamUtils.readString(in);
    }
    public static class Play_Server_NameItem_Builder {
        private Play_Server_NameItem object = new Play_Server_NameItem();
        /** Packet ID */
        public Play_Server_NameItem_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** The new name of the item */
        public Play_Server_NameItem_Builder itemName(String itemName) {
            object.itemName = itemName;
            return this;
        }
        public Play_Server_NameItem build() {
             return object;
        }

    }
    public static Play_Server_NameItem_Builder builder() {
        return new Play_Server_NameItem_Builder();
    }
}