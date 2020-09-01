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
@ServerPacket(0x0F)
public class Play_Client_ChatMessage extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x0F;
    /** Limited to 32767 bytes */
    @MCType("Chat")
    private String jsonData;
    /** 0: chat (chat box), 1: system message (chat box), 2: game info (above hotbar). */
    @MCType("Byte")
    private byte position;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Limited to 32767 bytes
        packet.write(ByteUtils.encodeChat(jsonData));
        // 0: chat (chat box), 1: system message (chat box), 2: game info (above hotbar).
        packet.write(ByteUtils.encodeByte(position));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Limited to 32767 bytes
        jsonData = StreamUtils.readChat(in);
        // 0: chat (chat box), 1: system message (chat box), 2: game info (above hotbar).
        position = StreamUtils.readByte(in);
    }
    public static class Play_Client_ChatMessage_Builder {
        private Play_Client_ChatMessage object = new Play_Client_ChatMessage();
        /** Packet ID */
        public Play_Client_ChatMessage_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Limited to 32767 bytes */
        public Play_Client_ChatMessage_Builder jsonData(String jsonData) {
            object.jsonData = jsonData;
            return this;
        }
        /** 0: chat (chat box), 1: system message (chat box), 2: game info (above hotbar). */
        public Play_Client_ChatMessage_Builder position(byte position) {
            object.position = position;
            return this;
        }
        public Play_Client_ChatMessage build() {
             return object;
        }

    }
    public static Play_Client_ChatMessage_Builder builder() {
        return new Play_Client_ChatMessage_Builder();
    }
}