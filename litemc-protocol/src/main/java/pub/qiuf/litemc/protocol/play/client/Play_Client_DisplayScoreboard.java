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
@ServerPacket(0x43)
public class Play_Client_DisplayScoreboard extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x43;
    /** The position of the scoreboard. 0: list, 1: sidebar, 2: below name, 3 - 18: team specific sidebar, indexed as 3 + team color. */
    @MCType("Byte")
    private byte position;
    /** The unique name for the scoreboard to be displayed. */
    @MCType("String")
    private String scoreName;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // The position of the scoreboard. 0: list, 1: sidebar, 2: below name, 3 - 18: team specific sidebar, indexed as 3 + team color.
        packet.write(ByteUtils.encodeByte(position));
        // The unique name for the scoreboard to be displayed.
        packet.write(ByteUtils.encodeString(scoreName));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // The position of the scoreboard. 0: list, 1: sidebar, 2: below name, 3 - 18: team specific sidebar, indexed as 3 + team color.
        position = StreamUtils.readByte(in);
        // The unique name for the scoreboard to be displayed.
        scoreName = StreamUtils.readString(in);
    }
    public static class Play_Client_DisplayScoreboard_Builder {
        private Play_Client_DisplayScoreboard object = new Play_Client_DisplayScoreboard();
        /** Packet ID */
        public Play_Client_DisplayScoreboard_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** The position of the scoreboard. 0: list, 1: sidebar, 2: below name, 3 - 18: team specific sidebar, indexed as 3 + team color. */
        public Play_Client_DisplayScoreboard_Builder position(byte position) {
            object.position = position;
            return this;
        }
        /** The unique name for the scoreboard to be displayed. */
        public Play_Client_DisplayScoreboard_Builder scoreName(String scoreName) {
            object.scoreName = scoreName;
            return this;
        }
        public Play_Client_DisplayScoreboard build() {
             return object;
        }

    }
    public static Play_Client_DisplayScoreboard_Builder builder() {
        return new Play_Client_DisplayScoreboard_Builder();
    }
}