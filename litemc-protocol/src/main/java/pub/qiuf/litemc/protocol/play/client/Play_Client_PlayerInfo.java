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
@ServerPacket(0x34)
public class Play_Client_PlayerInfo extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x34;
    /** Determines the rest of the Player format after the UUID */
    @MCType("VarInt")
    private int action;
    /** Number of elements in the following array */
    @MCType("VarInt")
    private int numberOfPlayers;
    @MCType("Play_Client_PlayerInfo_Player")
    private Play_Client_PlayerInfo_Player[] player;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Determines the rest of the Player format after the UUID
        packet.write(ByteUtils.encodeVarInt(action));
        // Number of elements in the following array
        packet.write(ByteUtils.encodeVarInt(numberOfPlayers));
        for (Play_Client_PlayerInfo_Player temp : player) {
            packet.write(temp.encode());
        }
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Determines the rest of the Player format after the UUID
        action = StreamUtils.readVarInt(in);
        // Number of elements in the following array
        numberOfPlayers = StreamUtils.readVarInt(in);
        player = new Play_Client_PlayerInfo_Player[numberOfPlayers];
        for (int i = 0; i < player.length; i++) {
            player[i] = new Play_Client_PlayerInfo_Player();
            player[i].decode(in);
        }
    }
    @Data
    public static class Play_Client_PlayerInfo_Player_AddPlayer_Property extends Packet {
        @MCType("String")
        private String name;
        @MCType("String")
        private String value;
        @MCType("Boolean")
        private boolean isSigned;
        /** Only if Is Signed is true */
        @MCType("String")
        private String signature;

        @Override
        public byte[] encode() throws Exception {
            ByteArrayDataOutput packet = ByteStreams.newDataOutput();
            packet.write(ByteUtils.encodeString(name));
            packet.write(ByteUtils.encodeString(value));
            packet.write(ByteUtils.encodeBoolean(isSigned));
            // Only if Is Signed is true
            packet.write(ByteUtils.encodeString(signature));
            return packet.toByteArray();
        }
        @Override
        public void decode(ByteArrayDataInput in) throws Exception {
            name = StreamUtils.readString(in);
            value = StreamUtils.readString(in);
            isSigned = StreamUtils.readBoolean(in);
            // Only if Is Signed is true
            signature = StreamUtils.readString(in);
        }
    }
    @FunctionalInterface
    public interface Play_Client_PlayerInfo_Player_AddPlayer_Property_Provider {
        Play_Client_PlayerInfo_Player_AddPlayer_Property[] provide();
    }
    public static Play_Client_PlayerInfo_Player_AddPlayer_Property_Builder propertyBuilder() {
        return new Play_Client_PlayerInfo_Player_AddPlayer_Property_Builder();
    }
    public static class Play_Client_PlayerInfo_Player_AddPlayer_Property_Builder {
        private Play_Client_PlayerInfo_Player_AddPlayer_Property object = new Play_Client_PlayerInfo_Player_AddPlayer_Property();
        public Play_Client_PlayerInfo_Player_AddPlayer_Property_Builder name(String name) {
            object.name = name;
            return this;
        }
        public Play_Client_PlayerInfo_Player_AddPlayer_Property_Builder value(String value) {
            object.value = value;
            return this;
        }
        public Play_Client_PlayerInfo_Player_AddPlayer_Property_Builder isSigned(boolean isSigned) {
            object.isSigned = isSigned;
            return this;
        }
        /** Only if Is Signed is true */
        public Play_Client_PlayerInfo_Player_AddPlayer_Property_Builder signature(String signature) {
            object.signature = signature;
            return this;
        }
        public Play_Client_PlayerInfo_Player_AddPlayer_Property build() {
             return object;
        }

    }
    @Data
    public static class Play_Client_PlayerInfo_Player_AddPlayer extends Packet {
        @MCType("String")
        private String name;
        /** Number of elements in the following array */
        @MCType("VarInt")
        private int numberOfProperties;
        @MCType("Play_Client_PlayerInfo_Player_AddPlayer_Property")
        private Play_Client_PlayerInfo_Player_AddPlayer_Property[] property;
        @MCType("VarInt")
        private int gamemode;
        /** Measured in milliseconds */
        @MCType("VarInt")
        private int ping;
        @MCType("Boolean")
        private boolean hasDisplayName;
        /** Only if Has Display Name is true */
        @MCType("Chat")
        private String displayName;

        @Override
        public byte[] encode() throws Exception {
            ByteArrayDataOutput packet = ByteStreams.newDataOutput();
            packet.write(ByteUtils.encodeString(name));
            // Number of elements in the following array
            packet.write(ByteUtils.encodeVarInt(numberOfProperties));
            for (Play_Client_PlayerInfo_Player_AddPlayer_Property temp : property) {
                packet.write(temp.encode());
            }
            packet.write(ByteUtils.encodeVarInt(gamemode));
            // Measured in milliseconds
            packet.write(ByteUtils.encodeVarInt(ping));
            packet.write(ByteUtils.encodeBoolean(hasDisplayName));
            // Only if Has Display Name is true
            packet.write(ByteUtils.encodeChat(displayName));
            return packet.toByteArray();
        }
        @Override
        public void decode(ByteArrayDataInput in) throws Exception {
            name = StreamUtils.readString(in);
            // Number of elements in the following array
            numberOfProperties = StreamUtils.readVarInt(in);
            property = new Play_Client_PlayerInfo_Player_AddPlayer_Property[numberOfProperties];
            for (int i = 0; i < property.length; i++) {
                property[i] = new Play_Client_PlayerInfo_Player_AddPlayer_Property();
                property[i].decode(in);
            }
            gamemode = StreamUtils.readVarInt(in);
            // Measured in milliseconds
            ping = StreamUtils.readVarInt(in);
            hasDisplayName = StreamUtils.readBoolean(in);
            // Only if Has Display Name is true
            displayName = StreamUtils.readChat(in);
        }
    }
    @FunctionalInterface
    public interface Play_Client_PlayerInfo_Player_AddPlayer_Provider {
        Play_Client_PlayerInfo_Player_AddPlayer provide();
    }
    public static Play_Client_PlayerInfo_Player_AddPlayer_Builder addPlayerBuilder() {
        return new Play_Client_PlayerInfo_Player_AddPlayer_Builder();
    }
    public static class Play_Client_PlayerInfo_Player_AddPlayer_Builder {
        private Play_Client_PlayerInfo_Player_AddPlayer object = new Play_Client_PlayerInfo_Player_AddPlayer();
        public Play_Client_PlayerInfo_Player_AddPlayer_Builder name(String name) {
            object.name = name;
            return this;
        }
        /** Number of elements in the following array */
        public Play_Client_PlayerInfo_Player_AddPlayer_Builder numberOfProperties(int numberOfProperties) {
            object.numberOfProperties = numberOfProperties;
            return this;
        }
        public Play_Client_PlayerInfo_Player_AddPlayer_Builder property(Play_Client_PlayerInfo_Player_AddPlayer_Property_Provider provider) {
            object.property = provider.provide();
            return this;
        }
        public Play_Client_PlayerInfo_Player_AddPlayer_Builder gamemode(int gamemode) {
            object.gamemode = gamemode;
            return this;
        }
        /** Measured in milliseconds */
        public Play_Client_PlayerInfo_Player_AddPlayer_Builder ping(int ping) {
            object.ping = ping;
            return this;
        }
        public Play_Client_PlayerInfo_Player_AddPlayer_Builder hasDisplayName(boolean hasDisplayName) {
            object.hasDisplayName = hasDisplayName;
            return this;
        }
        /** Only if Has Display Name is true */
        public Play_Client_PlayerInfo_Player_AddPlayer_Builder displayName(String displayName) {
            object.displayName = displayName;
            return this;
        }
        public Play_Client_PlayerInfo_Player_AddPlayer build() {
             return object;
        }

    }
    @Data
    public static class Play_Client_PlayerInfo_Player_UpdateDisplayName extends Packet {
        @MCType("Boolean")
        private boolean hasDisplayName;
        /** Only send if Has Display Name is true */
        @MCType("Chat")
        private String displayName;

        @Override
        public byte[] encode() throws Exception {
            ByteArrayDataOutput packet = ByteStreams.newDataOutput();
            packet.write(ByteUtils.encodeBoolean(hasDisplayName));
            // Only send if Has Display Name is true
            packet.write(ByteUtils.encodeChat(displayName));
            return packet.toByteArray();
        }
        @Override
        public void decode(ByteArrayDataInput in) throws Exception {
            hasDisplayName = StreamUtils.readBoolean(in);
            // Only send if Has Display Name is true
            displayName = StreamUtils.readChat(in);
        }
    }
    @FunctionalInterface
    public interface Play_Client_PlayerInfo_Player_UpdateDisplayName_Provider {
        Play_Client_PlayerInfo_Player_UpdateDisplayName provide();
    }
    public static Play_Client_PlayerInfo_Player_UpdateDisplayName_Builder updateDisplayNameBuilder() {
        return new Play_Client_PlayerInfo_Player_UpdateDisplayName_Builder();
    }
    public static class Play_Client_PlayerInfo_Player_UpdateDisplayName_Builder {
        private Play_Client_PlayerInfo_Player_UpdateDisplayName object = new Play_Client_PlayerInfo_Player_UpdateDisplayName();
        public Play_Client_PlayerInfo_Player_UpdateDisplayName_Builder hasDisplayName(boolean hasDisplayName) {
            object.hasDisplayName = hasDisplayName;
            return this;
        }
        /** Only send if Has Display Name is true */
        public Play_Client_PlayerInfo_Player_UpdateDisplayName_Builder displayName(String displayName) {
            object.displayName = displayName;
            return this;
        }
        public Play_Client_PlayerInfo_Player_UpdateDisplayName build() {
             return object;
        }

    }
    @Data
    public static class Play_Client_PlayerInfo_Player extends Packet {
        @MCType("UUID")
        private long[] uuid = new long[2];
        @MCType("Play_Client_PlayerInfo_Player_AddPlayer")
        private Play_Client_PlayerInfo_Player_AddPlayer addPlayer;
        @MCType("VarInt")
        private int gamemode;
        /** Measured in milliseconds */
        @MCType("VarInt")
        private int ping;
        @MCType("Play_Client_PlayerInfo_Player_UpdateDisplayName")
        private Play_Client_PlayerInfo_Player_UpdateDisplayName updateDisplayName;

        @Override
        public byte[] encode() throws Exception {
            ByteArrayDataOutput packet = ByteStreams.newDataOutput();
            for (long temp : uuid) {
                packet.write(ByteUtils.encodeLong(temp));
            }
            packet.write(addPlayer.encode());
            packet.write(ByteUtils.encodeVarInt(gamemode));
            // Measured in milliseconds
            packet.write(ByteUtils.encodeVarInt(ping));
            packet.write(updateDisplayName.encode());
            return packet.toByteArray();
        }
        @Override
        public void decode(ByteArrayDataInput in) throws Exception {
            // TODO decode uuid
            addPlayer = new Play_Client_PlayerInfo_Player_AddPlayer();
            addPlayer.decode(in);
            gamemode = StreamUtils.readVarInt(in);
            // Measured in milliseconds
            ping = StreamUtils.readVarInt(in);
            updateDisplayName = new Play_Client_PlayerInfo_Player_UpdateDisplayName();
            updateDisplayName.decode(in);
        }
    }
    @FunctionalInterface
    public interface Play_Client_PlayerInfo_Player_Provider {
        Play_Client_PlayerInfo_Player[] provide();
    }
    public static Play_Client_PlayerInfo_Player_Builder playerBuilder() {
        return new Play_Client_PlayerInfo_Player_Builder();
    }
    public static class Play_Client_PlayerInfo_Player_Builder {
        private Play_Client_PlayerInfo_Player object = new Play_Client_PlayerInfo_Player();
        public Play_Client_PlayerInfo_Player_Builder uuid(long[] uuid) {
            object.uuid = uuid;
            return this;
        }
        public Play_Client_PlayerInfo_Player_Builder addPlayer(Play_Client_PlayerInfo_Player_AddPlayer_Provider provider) {
            object.addPlayer = provider.provide();
            return this;
        }
        public Play_Client_PlayerInfo_Player_Builder gamemode(int gamemode) {
            object.gamemode = gamemode;
            return this;
        }
        /** Measured in milliseconds */
        public Play_Client_PlayerInfo_Player_Builder ping(int ping) {
            object.ping = ping;
            return this;
        }
        public Play_Client_PlayerInfo_Player_Builder updateDisplayName(Play_Client_PlayerInfo_Player_UpdateDisplayName_Provider provider) {
            object.updateDisplayName = provider.provide();
            return this;
        }
        public Play_Client_PlayerInfo_Player build() {
             return object;
        }

    }
    public static class Play_Client_PlayerInfo_Builder {
        private Play_Client_PlayerInfo object = new Play_Client_PlayerInfo();
        /** Packet ID */
        public Play_Client_PlayerInfo_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Determines the rest of the Player format after the UUID */
        public Play_Client_PlayerInfo_Builder action(int action) {
            object.action = action;
            return this;
        }
        /** Number of elements in the following array */
        public Play_Client_PlayerInfo_Builder numberOfPlayers(int numberOfPlayers) {
            object.numberOfPlayers = numberOfPlayers;
            return this;
        }
        public Play_Client_PlayerInfo_Builder player(Play_Client_PlayerInfo_Player_Provider provider) {
            object.player = provider.provide();
            return this;
        }
        public Play_Client_PlayerInfo build() {
             return object;
        }

    }
    public static Play_Client_PlayerInfo_Builder builder() {
        return new Play_Client_PlayerInfo_Builder();
    }
}