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
@ServerPacket(0x19)
public class Play_Client_PluginMessage extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x19;
    /** Name of the plugin channel used to send the data */
    @MCType("Identifier")
    private String channel;
    /** Any data, depending on the channel. minecraft: channels are documented here. The length of this array must be inferred from the packet length. */
    @MCType("Byte")
    private byte[] data;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Name of the plugin channel used to send the data
        packet.write(ByteUtils.encodeIdentifier(channel));
        // Any data, depending on the channel. minecraft: channels are documented here. The length of this array must be inferred from the packet length.
        packet.write(data);
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Name of the plugin channel used to send the data
        channel = StreamUtils.readIdentifier(in);
        // Any data, depending on the channel. minecraft: channels are documented here. The length of this array must be inferred from the packet length.
        // TODO decode data
    }
    public static class Play_Client_PluginMessage_Builder {
        private Play_Client_PluginMessage object = new Play_Client_PluginMessage();
        /** Packet ID */
        public Play_Client_PluginMessage_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Name of the plugin channel used to send the data */
        public Play_Client_PluginMessage_Builder channel(String channel) {
            object.channel = channel;
            return this;
        }
        /** Any data, depending on the channel. minecraft: channels are documented here. The length of this array must be inferred from the packet length. */
        public Play_Client_PluginMessage_Builder data(byte[] data) {
            object.data = data;
            return this;
        }
        public Play_Client_PluginMessage build() {
             return object;
        }

    }
    public static Play_Client_PluginMessage_Builder builder() {
        return new Play_Client_PluginMessage_Builder();
    }
}