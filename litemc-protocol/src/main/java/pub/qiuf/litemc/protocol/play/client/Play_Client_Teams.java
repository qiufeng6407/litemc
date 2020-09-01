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
@ServerPacket(0x4C)
public class Play_Client_Teams extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x4C;
    /** A unique name for the team. (Shared with scoreboard). */
    @MCType("String")
    private String teamName;
    /** Determines the layout of the remaining packet */
    @MCType("Byte")
    private byte mode;
    @MCType("Play_Client_Teams_CreateTeam")
    private Play_Client_Teams_CreateTeam createTeam;
    @MCType("Play_Client_Teams_UpdateTeamInfo")
    private Play_Client_Teams_UpdateTeamInfo updateTeamInfo;
    @MCType("Play_Client_Teams_AddPlayersToTeam")
    private Play_Client_Teams_AddPlayersToTeam addPlayersToTeam;
    @MCType("Play_Client_Teams_RemovePlayersFromTeam")
    private Play_Client_Teams_RemovePlayersFromTeam removePlayersFromTeam;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // A unique name for the team. (Shared with scoreboard).
        packet.write(ByteUtils.encodeString(teamName));
        // Determines the layout of the remaining packet
        packet.write(ByteUtils.encodeByte(mode));
        packet.write(createTeam.encode());
        packet.write(updateTeamInfo.encode());
        packet.write(addPlayersToTeam.encode());
        packet.write(removePlayersFromTeam.encode());
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // A unique name for the team. (Shared with scoreboard).
        teamName = StreamUtils.readString(in);
        // Determines the layout of the remaining packet
        mode = StreamUtils.readByte(in);
        createTeam = new Play_Client_Teams_CreateTeam();
        createTeam.decode(in);
        updateTeamInfo = new Play_Client_Teams_UpdateTeamInfo();
        updateTeamInfo.decode(in);
        addPlayersToTeam = new Play_Client_Teams_AddPlayersToTeam();
        addPlayersToTeam.decode(in);
        removePlayersFromTeam = new Play_Client_Teams_RemovePlayersFromTeam();
        removePlayersFromTeam.decode(in);
    }
    @Data
    public static class Play_Client_Teams_CreateTeam extends Packet {
        @MCType("Chat")
        private String teamDisplayName;
        /** Bit mask. 0x01: Allow friendly fire, 0x02: can see invisible players on same team */
        @MCType("Byte")
        private byte friendlyFlags;
        /** always, hideForOtherTeams, hideForOwnTeam, never */
        @MCType("String")
        private String nameTagVisibility;
        /** always, pushOtherTeams, pushOwnTeam, never */
        @MCType("String")
        private String collisionRule;
        /** Used to color the name of players on the team; see below */
        @MCType("VarInt")
        private int teamColor;
        /** Displayed before the names of players that are part of this team */
        @MCType("Chat")
        private String teamPrefix;
        /** Displayed after the names of players that are part of this team */
        @MCType("Chat")
        private String teamSuffix;
        /** Number of elements in the following array */
        @MCType("VarInt")
        private int entityCount;
        /** Identifiers for the entities in this team. For players, this is their username; for other entities, it is their UUID. */
        @MCType("String")
        private String[] entities;

        @Override
        public byte[] encode() throws Exception {
            ByteArrayDataOutput packet = ByteStreams.newDataOutput();
            packet.write(ByteUtils.encodeChat(teamDisplayName));
            // Bit mask. 0x01: Allow friendly fire, 0x02: can see invisible players on same team
            packet.write(ByteUtils.encodeByte(friendlyFlags));
            // always, hideForOtherTeams, hideForOwnTeam, never
            packet.write(ByteUtils.encodeString(nameTagVisibility));
            // always, pushOtherTeams, pushOwnTeam, never
            packet.write(ByteUtils.encodeString(collisionRule));
            // Used to color the name of players on the team; see below
            packet.write(ByteUtils.encodeVarInt(teamColor));
            // Displayed before the names of players that are part of this team
            packet.write(ByteUtils.encodeChat(teamPrefix));
            // Displayed after the names of players that are part of this team
            packet.write(ByteUtils.encodeChat(teamSuffix));
            // Number of elements in the following array
            packet.write(ByteUtils.encodeVarInt(entityCount));
            // Identifiers for the entities in this team. For players, this is their username; for other entities, it is their UUID.
            for (String temp : entities) {
                packet.write(ByteUtils.encodeString(temp));
            }
            return packet.toByteArray();
        }
        @Override
        public void decode(ByteArrayDataInput in) throws Exception {
            teamDisplayName = StreamUtils.readChat(in);
            // Bit mask. 0x01: Allow friendly fire, 0x02: can see invisible players on same team
            friendlyFlags = StreamUtils.readByte(in);
            // always, hideForOtherTeams, hideForOwnTeam, never
            nameTagVisibility = StreamUtils.readString(in);
            // always, pushOtherTeams, pushOwnTeam, never
            collisionRule = StreamUtils.readString(in);
            // Used to color the name of players on the team; see below
            teamColor = StreamUtils.readVarInt(in);
            // Displayed before the names of players that are part of this team
            teamPrefix = StreamUtils.readChat(in);
            // Displayed after the names of players that are part of this team
            teamSuffix = StreamUtils.readChat(in);
            // Number of elements in the following array
            entityCount = StreamUtils.readVarInt(in);
            // Identifiers for the entities in this team. For players, this is their username; for other entities, it is their UUID.
            entities = new String[entityCount];
            for (int i = 0; i < entities.length; i++) {
                entities[i] = StreamUtils.readString(in);
            }
        }
    }
    @FunctionalInterface
    public interface Play_Client_Teams_CreateTeam_Provider {
        Play_Client_Teams_CreateTeam provide();
    }
    public static Play_Client_Teams_CreateTeam_Builder createTeamBuilder() {
        return new Play_Client_Teams_CreateTeam_Builder();
    }
    public static class Play_Client_Teams_CreateTeam_Builder {
        private Play_Client_Teams_CreateTeam object = new Play_Client_Teams_CreateTeam();
        public Play_Client_Teams_CreateTeam_Builder teamDisplayName(String teamDisplayName) {
            object.teamDisplayName = teamDisplayName;
            return this;
        }
        /** Bit mask. 0x01: Allow friendly fire, 0x02: can see invisible players on same team */
        public Play_Client_Teams_CreateTeam_Builder friendlyFlags(byte friendlyFlags) {
            object.friendlyFlags = friendlyFlags;
            return this;
        }
        /** always, hideForOtherTeams, hideForOwnTeam, never */
        public Play_Client_Teams_CreateTeam_Builder nameTagVisibility(String nameTagVisibility) {
            object.nameTagVisibility = nameTagVisibility;
            return this;
        }
        /** always, pushOtherTeams, pushOwnTeam, never */
        public Play_Client_Teams_CreateTeam_Builder collisionRule(String collisionRule) {
            object.collisionRule = collisionRule;
            return this;
        }
        /** Used to color the name of players on the team; see below */
        public Play_Client_Teams_CreateTeam_Builder teamColor(int teamColor) {
            object.teamColor = teamColor;
            return this;
        }
        /** Displayed before the names of players that are part of this team */
        public Play_Client_Teams_CreateTeam_Builder teamPrefix(String teamPrefix) {
            object.teamPrefix = teamPrefix;
            return this;
        }
        /** Displayed after the names of players that are part of this team */
        public Play_Client_Teams_CreateTeam_Builder teamSuffix(String teamSuffix) {
            object.teamSuffix = teamSuffix;
            return this;
        }
        /** Number of elements in the following array */
        public Play_Client_Teams_CreateTeam_Builder entityCount(int entityCount) {
            object.entityCount = entityCount;
            return this;
        }
        /** Identifiers for the entities in this team. For players, this is their username; for other entities, it is their UUID. */
        public Play_Client_Teams_CreateTeam_Builder entities(String[] entities) {
            object.entities = entities;
            return this;
        }
        public Play_Client_Teams_CreateTeam build() {
             return object;
        }

    }
    @Data
    public static class Play_Client_Teams_UpdateTeamInfo extends Packet {
        @MCType("Chat")
        private String teamDisplayName;
        /** Bit mask. 0x01: Allow friendly fire, 0x02: can see invisible entities on same team */
        @MCType("Byte")
        private byte friendlyFlags;
        /** always, hideForOtherTeams, hideForOwnTeam, never */
        @MCType("String")
        private String nameTagVisibility;
        /** always, pushOtherTeams, pushOwnTeam, never */
        @MCType("String")
        private String collisionRule;
        /** Used to color the name of players on the team; see below */
        @MCType("VarInt")
        private int teamColor;
        /** Displayed before the names of players that are part of this team */
        @MCType("Chat")
        private String teamPrefix;
        /** Displayed after the names of players that are part of this team */
        @MCType("Chat")
        private String teamSuffix;

        @Override
        public byte[] encode() throws Exception {
            ByteArrayDataOutput packet = ByteStreams.newDataOutput();
            packet.write(ByteUtils.encodeChat(teamDisplayName));
            // Bit mask. 0x01: Allow friendly fire, 0x02: can see invisible entities on same team
            packet.write(ByteUtils.encodeByte(friendlyFlags));
            // always, hideForOtherTeams, hideForOwnTeam, never
            packet.write(ByteUtils.encodeString(nameTagVisibility));
            // always, pushOtherTeams, pushOwnTeam, never
            packet.write(ByteUtils.encodeString(collisionRule));
            // Used to color the name of players on the team; see below
            packet.write(ByteUtils.encodeVarInt(teamColor));
            // Displayed before the names of players that are part of this team
            packet.write(ByteUtils.encodeChat(teamPrefix));
            // Displayed after the names of players that are part of this team
            packet.write(ByteUtils.encodeChat(teamSuffix));
            return packet.toByteArray();
        }
        @Override
        public void decode(ByteArrayDataInput in) throws Exception {
            teamDisplayName = StreamUtils.readChat(in);
            // Bit mask. 0x01: Allow friendly fire, 0x02: can see invisible entities on same team
            friendlyFlags = StreamUtils.readByte(in);
            // always, hideForOtherTeams, hideForOwnTeam, never
            nameTagVisibility = StreamUtils.readString(in);
            // always, pushOtherTeams, pushOwnTeam, never
            collisionRule = StreamUtils.readString(in);
            // Used to color the name of players on the team; see below
            teamColor = StreamUtils.readVarInt(in);
            // Displayed before the names of players that are part of this team
            teamPrefix = StreamUtils.readChat(in);
            // Displayed after the names of players that are part of this team
            teamSuffix = StreamUtils.readChat(in);
        }
    }
    @FunctionalInterface
    public interface Play_Client_Teams_UpdateTeamInfo_Provider {
        Play_Client_Teams_UpdateTeamInfo provide();
    }
    public static Play_Client_Teams_UpdateTeamInfo_Builder updateTeamInfoBuilder() {
        return new Play_Client_Teams_UpdateTeamInfo_Builder();
    }
    public static class Play_Client_Teams_UpdateTeamInfo_Builder {
        private Play_Client_Teams_UpdateTeamInfo object = new Play_Client_Teams_UpdateTeamInfo();
        public Play_Client_Teams_UpdateTeamInfo_Builder teamDisplayName(String teamDisplayName) {
            object.teamDisplayName = teamDisplayName;
            return this;
        }
        /** Bit mask. 0x01: Allow friendly fire, 0x02: can see invisible entities on same team */
        public Play_Client_Teams_UpdateTeamInfo_Builder friendlyFlags(byte friendlyFlags) {
            object.friendlyFlags = friendlyFlags;
            return this;
        }
        /** always, hideForOtherTeams, hideForOwnTeam, never */
        public Play_Client_Teams_UpdateTeamInfo_Builder nameTagVisibility(String nameTagVisibility) {
            object.nameTagVisibility = nameTagVisibility;
            return this;
        }
        /** always, pushOtherTeams, pushOwnTeam, never */
        public Play_Client_Teams_UpdateTeamInfo_Builder collisionRule(String collisionRule) {
            object.collisionRule = collisionRule;
            return this;
        }
        /** Used to color the name of players on the team; see below */
        public Play_Client_Teams_UpdateTeamInfo_Builder teamColor(int teamColor) {
            object.teamColor = teamColor;
            return this;
        }
        /** Displayed before the names of players that are part of this team */
        public Play_Client_Teams_UpdateTeamInfo_Builder teamPrefix(String teamPrefix) {
            object.teamPrefix = teamPrefix;
            return this;
        }
        /** Displayed after the names of players that are part of this team */
        public Play_Client_Teams_UpdateTeamInfo_Builder teamSuffix(String teamSuffix) {
            object.teamSuffix = teamSuffix;
            return this;
        }
        public Play_Client_Teams_UpdateTeamInfo build() {
             return object;
        }

    }
    @Data
    public static class Play_Client_Teams_AddPlayersToTeam extends Packet {
        /** Number of elements in the following array */
        @MCType("VarInt")
        private int entityCount;
        /** Identifiers for the entities added. For players, this is their username; for other entities, it is their UUID. */
        @MCType("String")
        private String[] entities;

        @Override
        public byte[] encode() throws Exception {
            ByteArrayDataOutput packet = ByteStreams.newDataOutput();
            // Number of elements in the following array
            packet.write(ByteUtils.encodeVarInt(entityCount));
            // Identifiers for the entities added. For players, this is their username; for other entities, it is their UUID.
            for (String temp : entities) {
                packet.write(ByteUtils.encodeString(temp));
            }
            return packet.toByteArray();
        }
        @Override
        public void decode(ByteArrayDataInput in) throws Exception {
            // Number of elements in the following array
            entityCount = StreamUtils.readVarInt(in);
            // Identifiers for the entities added. For players, this is their username; for other entities, it is their UUID.
            entities = new String[entityCount];
            for (int i = 0; i < entities.length; i++) {
                entities[i] = StreamUtils.readString(in);
            }
        }
    }
    @FunctionalInterface
    public interface Play_Client_Teams_AddPlayersToTeam_Provider {
        Play_Client_Teams_AddPlayersToTeam provide();
    }
    public static Play_Client_Teams_AddPlayersToTeam_Builder addPlayersToTeamBuilder() {
        return new Play_Client_Teams_AddPlayersToTeam_Builder();
    }
    public static class Play_Client_Teams_AddPlayersToTeam_Builder {
        private Play_Client_Teams_AddPlayersToTeam object = new Play_Client_Teams_AddPlayersToTeam();
        /** Number of elements in the following array */
        public Play_Client_Teams_AddPlayersToTeam_Builder entityCount(int entityCount) {
            object.entityCount = entityCount;
            return this;
        }
        /** Identifiers for the entities added. For players, this is their username; for other entities, it is their UUID. */
        public Play_Client_Teams_AddPlayersToTeam_Builder entities(String[] entities) {
            object.entities = entities;
            return this;
        }
        public Play_Client_Teams_AddPlayersToTeam build() {
             return object;
        }

    }
    @Data
    public static class Play_Client_Teams_RemovePlayersFromTeam extends Packet {
        /** Number of elements in the following array */
        @MCType("VarInt")
        private int entityCount;
        /** Identifiers for the entities removed. For players, this is their username; for other entities, it is their UUID. */
        @MCType("String")
        private String[] entities;

        @Override
        public byte[] encode() throws Exception {
            ByteArrayDataOutput packet = ByteStreams.newDataOutput();
            // Number of elements in the following array
            packet.write(ByteUtils.encodeVarInt(entityCount));
            // Identifiers for the entities removed. For players, this is their username; for other entities, it is their UUID.
            for (String temp : entities) {
                packet.write(ByteUtils.encodeString(temp));
            }
            return packet.toByteArray();
        }
        @Override
        public void decode(ByteArrayDataInput in) throws Exception {
            // Number of elements in the following array
            entityCount = StreamUtils.readVarInt(in);
            // Identifiers for the entities removed. For players, this is their username; for other entities, it is their UUID.
            entities = new String[entityCount];
            for (int i = 0; i < entities.length; i++) {
                entities[i] = StreamUtils.readString(in);
            }
        }
    }
    @FunctionalInterface
    public interface Play_Client_Teams_RemovePlayersFromTeam_Provider {
        Play_Client_Teams_RemovePlayersFromTeam provide();
    }
    public static Play_Client_Teams_RemovePlayersFromTeam_Builder removePlayersFromTeamBuilder() {
        return new Play_Client_Teams_RemovePlayersFromTeam_Builder();
    }
    public static class Play_Client_Teams_RemovePlayersFromTeam_Builder {
        private Play_Client_Teams_RemovePlayersFromTeam object = new Play_Client_Teams_RemovePlayersFromTeam();
        /** Number of elements in the following array */
        public Play_Client_Teams_RemovePlayersFromTeam_Builder entityCount(int entityCount) {
            object.entityCount = entityCount;
            return this;
        }
        /** Identifiers for the entities removed. For players, this is their username; for other entities, it is their UUID. */
        public Play_Client_Teams_RemovePlayersFromTeam_Builder entities(String[] entities) {
            object.entities = entities;
            return this;
        }
        public Play_Client_Teams_RemovePlayersFromTeam build() {
             return object;
        }

    }
    public static class Play_Client_Teams_Builder {
        private Play_Client_Teams object = new Play_Client_Teams();
        /** Packet ID */
        public Play_Client_Teams_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** A unique name for the team. (Shared with scoreboard). */
        public Play_Client_Teams_Builder teamName(String teamName) {
            object.teamName = teamName;
            return this;
        }
        /** Determines the layout of the remaining packet */
        public Play_Client_Teams_Builder mode(byte mode) {
            object.mode = mode;
            return this;
        }
        public Play_Client_Teams_Builder createTeam(Play_Client_Teams_CreateTeam_Provider provider) {
            object.createTeam = provider.provide();
            return this;
        }
        public Play_Client_Teams_Builder updateTeamInfo(Play_Client_Teams_UpdateTeamInfo_Provider provider) {
            object.updateTeamInfo = provider.provide();
            return this;
        }
        public Play_Client_Teams_Builder addPlayersToTeam(Play_Client_Teams_AddPlayersToTeam_Provider provider) {
            object.addPlayersToTeam = provider.provide();
            return this;
        }
        public Play_Client_Teams_Builder removePlayersFromTeam(Play_Client_Teams_RemovePlayersFromTeam_Provider provider) {
            object.removePlayersFromTeam = provider.provide();
            return this;
        }
        public Play_Client_Teams build() {
             return object;
        }

    }
    public static Play_Client_Teams_Builder builder() {
        return new Play_Client_Teams_Builder();
    }
}