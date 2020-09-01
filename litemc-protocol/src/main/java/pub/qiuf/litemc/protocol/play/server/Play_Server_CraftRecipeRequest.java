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
public class Play_Server_CraftRecipeRequest extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x18;
    @MCType("Byte")
    private byte windowId;
    /** A recipe ID */
    @MCType("Identifier")
    private String recipe;
    /** Affects the amount of items processed; true if shift is down when clicked */
    @MCType("Boolean")
    private boolean makeAll;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        packet.write(ByteUtils.encodeByte(windowId));
        // A recipe ID
        packet.write(ByteUtils.encodeIdentifier(recipe));
        // Affects the amount of items processed; true if shift is down when clicked
        packet.write(ByteUtils.encodeBoolean(makeAll));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        windowId = StreamUtils.readByte(in);
        // A recipe ID
        recipe = StreamUtils.readIdentifier(in);
        // Affects the amount of items processed; true if shift is down when clicked
        makeAll = StreamUtils.readBoolean(in);
    }
    public static class Play_Server_CraftRecipeRequest_Builder {
        private Play_Server_CraftRecipeRequest object = new Play_Server_CraftRecipeRequest();
        /** Packet ID */
        public Play_Server_CraftRecipeRequest_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        public Play_Server_CraftRecipeRequest_Builder windowId(byte windowId) {
            object.windowId = windowId;
            return this;
        }
        /** A recipe ID */
        public Play_Server_CraftRecipeRequest_Builder recipe(String recipe) {
            object.recipe = recipe;
            return this;
        }
        /** Affects the amount of items processed; true if shift is down when clicked */
        public Play_Server_CraftRecipeRequest_Builder makeAll(boolean makeAll) {
            object.makeAll = makeAll;
            return this;
        }
        public Play_Server_CraftRecipeRequest build() {
             return object;
        }

    }
    public static Play_Server_CraftRecipeRequest_Builder builder() {
        return new Play_Server_CraftRecipeRequest_Builder();
    }
}