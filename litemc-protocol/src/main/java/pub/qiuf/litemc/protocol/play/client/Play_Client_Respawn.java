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
@ServerPacket(0x3B)
public class Play_Client_Respawn extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x3B;
    /** -1: The Nether, 0: The Overworld, 1: The End */
    @MCType("Int")
    private int dimension;
    /** First 8 bytes of the SHA-256 hash of the world's seed. */
    @MCType("Long")
    private long hashedSeed;
    /** 0: survival, 1: creative, 2: adventure, 3: spectator. The hardcore flag is not included */
    @MCType("UnsignedByte")
    private int gamemode;
    /** Same as Join Game */
    @MCType("String")
    private String levelType;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // -1: The Nether, 0: The Overworld, 1: The End
        packet.write(ByteUtils.encodeInt(dimension));
        // First 8 bytes of the SHA-256 hash of the world's seed.
        packet.write(ByteUtils.encodeLong(hashedSeed));
        // 0: survival, 1: creative, 2: adventure, 3: spectator. The hardcore flag is not included
        packet.write(ByteUtils.encodeUnsignedByte(gamemode));
        // Same as Join Game
        packet.write(ByteUtils.encodeString(levelType));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // -1: The Nether, 0: The Overworld, 1: The End
        dimension = StreamUtils.readInt(in);
        // First 8 bytes of the SHA-256 hash of the world's seed.
        hashedSeed = StreamUtils.readLong(in);
        // 0: survival, 1: creative, 2: adventure, 3: spectator. The hardcore flag is not included
        gamemode = StreamUtils.readUnsignedByte(in);
        // Same as Join Game
        levelType = StreamUtils.readString(in);
    }
    public static class Play_Client_Respawn_Builder {
        private Play_Client_Respawn object = new Play_Client_Respawn();
        /** Packet ID */
        public Play_Client_Respawn_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** -1: The Nether, 0: The Overworld, 1: The End */
        public Play_Client_Respawn_Builder dimension(int dimension) {
            object.dimension = dimension;
            return this;
        }
        /** First 8 bytes of the SHA-256 hash of the world's seed. */
        public Play_Client_Respawn_Builder hashedSeed(long hashedSeed) {
            object.hashedSeed = hashedSeed;
            return this;
        }
        /** 0: survival, 1: creative, 2: adventure, 3: spectator. The hardcore flag is not included */
        public Play_Client_Respawn_Builder gamemode(int gamemode) {
            object.gamemode = gamemode;
            return this;
        }
        /** Same as Join Game */
        public Play_Client_Respawn_Builder levelType(String levelType) {
            object.levelType = levelType;
            return this;
        }
        public Play_Client_Respawn build() {
             return object;
        }

    }
    public static Play_Client_Respawn_Builder builder() {
        return new Play_Client_Respawn_Builder();
    }
}