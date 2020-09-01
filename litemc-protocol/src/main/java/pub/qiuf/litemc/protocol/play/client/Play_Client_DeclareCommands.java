package pub.qiuf.litemc.protocol.play.client;

import pub.qiuf.litemc.common.bean.datatype.Packet;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import pub.qiuf.litemc.common.util.ByteUtils;
import pub.qiuf.litemc.common.bean.datatype.Node;
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
@ServerPacket(0x12)
public class Play_Client_DeclareCommands extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x12;
    /** Number of elements in the following array */
    @MCType("VarInt")
    private int count;
    /** An array of nodes */
    @MCType("Node")
    private Node[] nodes;
    /** Index of the root node in the previous array */
    @MCType("VarInt")
    private int rootIndex;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Number of elements in the following array
        packet.write(ByteUtils.encodeVarInt(count));
        // An array of nodes
        for (Node temp : nodes) {
            packet.write(ByteUtils.encodeNode(temp));
        }
        // Index of the root node in the previous array
        packet.write(ByteUtils.encodeVarInt(rootIndex));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Number of elements in the following array
        count = StreamUtils.readVarInt(in);
        // An array of nodes
        nodes = new Node[count];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = StreamUtils.readNode(in);
        }
        // Index of the root node in the previous array
        rootIndex = StreamUtils.readVarInt(in);
    }
    public static class Play_Client_DeclareCommands_Builder {
        private Play_Client_DeclareCommands object = new Play_Client_DeclareCommands();
        /** Packet ID */
        public Play_Client_DeclareCommands_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Number of elements in the following array */
        public Play_Client_DeclareCommands_Builder count(int count) {
            object.count = count;
            return this;
        }
        /** An array of nodes */
        public Play_Client_DeclareCommands_Builder nodes(Node[] nodes) {
            object.nodes = nodes;
            return this;
        }
        /** Index of the root node in the previous array */
        public Play_Client_DeclareCommands_Builder rootIndex(int rootIndex) {
            object.rootIndex = rootIndex;
            return this;
        }
        public Play_Client_DeclareCommands build() {
             return object;
        }

    }
    public static Play_Client_DeclareCommands_Builder builder() {
        return new Play_Client_DeclareCommands_Builder();
    }
}