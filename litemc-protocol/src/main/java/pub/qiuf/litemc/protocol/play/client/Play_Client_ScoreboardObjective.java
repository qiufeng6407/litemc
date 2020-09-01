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
@ServerPacket(0x4A)
public class Play_Client_ScoreboardObjective extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x4A;
    /** An unique name for the objective */
    @MCType("String")
    private String objectiveName;
    /** 0 to create the scoreboard. 1 to remove the scoreboard. 2 to update the display text. */
    @MCType("Byte")
    private byte mode;
    /** Only if mode is 0 or 2. The text to be displayed for the score */
    @MCType("Chat")
    private String objectiveValue;
    /** Only if mode is 0 or 2. 0 = "integer", 1 = "hearts". */
    @MCType("VarInt")
    private int type;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // An unique name for the objective
        packet.write(ByteUtils.encodeString(objectiveName));
        // 0 to create the scoreboard. 1 to remove the scoreboard. 2 to update the display text.
        packet.write(ByteUtils.encodeByte(mode));
        // Only if mode is 0 or 2. The text to be displayed for the score
        packet.write(ByteUtils.encodeChat(objectiveValue));
        // Only if mode is 0 or 2. 0 = "integer", 1 = "hearts".
        packet.write(ByteUtils.encodeVarInt(type));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // An unique name for the objective
        objectiveName = StreamUtils.readString(in);
        // 0 to create the scoreboard. 1 to remove the scoreboard. 2 to update the display text.
        mode = StreamUtils.readByte(in);
        // Only if mode is 0 or 2. The text to be displayed for the score
        objectiveValue = StreamUtils.readChat(in);
        // Only if mode is 0 or 2. 0 = "integer", 1 = "hearts".
        type = StreamUtils.readVarInt(in);
    }
    public static class Play_Client_ScoreboardObjective_Builder {
        private Play_Client_ScoreboardObjective object = new Play_Client_ScoreboardObjective();
        /** Packet ID */
        public Play_Client_ScoreboardObjective_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** An unique name for the objective */
        public Play_Client_ScoreboardObjective_Builder objectiveName(String objectiveName) {
            object.objectiveName = objectiveName;
            return this;
        }
        /** 0 to create the scoreboard. 1 to remove the scoreboard. 2 to update the display text. */
        public Play_Client_ScoreboardObjective_Builder mode(byte mode) {
            object.mode = mode;
            return this;
        }
        /** Only if mode is 0 or 2. The text to be displayed for the score */
        public Play_Client_ScoreboardObjective_Builder objectiveValue(String objectiveValue) {
            object.objectiveValue = objectiveValue;
            return this;
        }
        /** Only if mode is 0 or 2. 0 = "integer", 1 = "hearts". */
        public Play_Client_ScoreboardObjective_Builder type(int type) {
            object.type = type;
            return this;
        }
        public Play_Client_ScoreboardObjective build() {
             return object;
        }

    }
    public static Play_Client_ScoreboardObjective_Builder builder() {
        return new Play_Client_ScoreboardObjective_Builder();
    }
}