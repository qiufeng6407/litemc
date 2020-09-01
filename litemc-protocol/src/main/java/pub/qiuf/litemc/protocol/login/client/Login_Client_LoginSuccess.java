package pub.qiuf.litemc.protocol.login.client;

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
@ServerPacket(0x02)
public class Login_Client_LoginSuccess extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x02;
    /** Unlike in other packets, this field contains the UUID as a string with hyphens. */
    @MCType("String")
    private String uuid;
    @MCType("String")
    private String username;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // Unlike in other packets, this field contains the UUID as a string with hyphens.
        packet.write(ByteUtils.encodeString(uuid));
        packet.write(ByteUtils.encodeString(username));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // Unlike in other packets, this field contains the UUID as a string with hyphens.
        uuid = StreamUtils.readString(in);
        username = StreamUtils.readString(in);
    }
    public static class Login_Client_LoginSuccess_Builder {
        private Login_Client_LoginSuccess object = new Login_Client_LoginSuccess();
        /** Packet ID */
        public Login_Client_LoginSuccess_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** Unlike in other packets, this field contains the UUID as a string with hyphens. */
        public Login_Client_LoginSuccess_Builder uuid(String uuid) {
            object.uuid = uuid;
            return this;
        }
        public Login_Client_LoginSuccess_Builder username(String username) {
            object.username = username;
            return this;
        }
        public Login_Client_LoginSuccess build() {
             return object;
        }

    }
    public static Login_Client_LoginSuccess_Builder builder() {
        return new Login_Client_LoginSuccess_Builder();
    }
}