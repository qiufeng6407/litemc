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
public class Play_Server_UpdateCommandBlockMinecart extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x25;
    @MCType("VarInt")
    private int entityId;
    @MCType("String")
    private String command;
    /** If false, the output of the previous command will not be stored within the command block. */
    @MCType("Boolean")
    private boolean trackOutput;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        packet.write(ByteUtils.encodeVarInt(entityId));
        packet.write(ByteUtils.encodeString(command));
        // If false, the output of the previous command will not be stored within the command block.
        packet.write(ByteUtils.encodeBoolean(trackOutput));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        entityId = StreamUtils.readVarInt(in);
        command = StreamUtils.readString(in);
        // If false, the output of the previous command will not be stored within the command block.
        trackOutput = StreamUtils.readBoolean(in);
    }
    public static class Play_Server_UpdateCommandBlockMinecart_Builder {
        private Play_Server_UpdateCommandBlockMinecart object = new Play_Server_UpdateCommandBlockMinecart();
        /** Packet ID */
        public Play_Server_UpdateCommandBlockMinecart_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        public Play_Server_UpdateCommandBlockMinecart_Builder entityId(int entityId) {
            object.entityId = entityId;
            return this;
        }
        public Play_Server_UpdateCommandBlockMinecart_Builder command(String command) {
            object.command = command;
            return this;
        }
        /** If false, the output of the previous command will not be stored within the command block. */
        public Play_Server_UpdateCommandBlockMinecart_Builder trackOutput(boolean trackOutput) {
            object.trackOutput = trackOutput;
            return this;
        }
        public Play_Server_UpdateCommandBlockMinecart build() {
             return object;
        }

    }
    public static Play_Server_UpdateCommandBlockMinecart_Builder builder() {
        return new Play_Server_UpdateCommandBlockMinecart_Builder();
    }
}