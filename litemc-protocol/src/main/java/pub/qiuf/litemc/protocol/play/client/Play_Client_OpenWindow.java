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
@ServerPacket(0x2F)
public class Play_Client_OpenWindow extends Packet {
    /** Packet ID */
    @MCType("VarInt")
    private int packetId = 0x2F;
    /** A unique id number for the window to be displayed. Notchian server implementation is a counter, starting at 1. */
    @MCType("VarInt")
    private int windowId;
    /** The window type to use for display. Contained in the minecraft:menu regisry; see Inventory for the different values. */
    @MCType("VarInt")
    private int windowType;
    /** The title of the window */
    @MCType("Chat")
    private String windowTitle;

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        // Packet ID
        packet.write(ByteUtils.encodeVarInt(packetId));
        // A unique id number for the window to be displayed. Notchian server implementation is a counter, starting at 1.
        packet.write(ByteUtils.encodeVarInt(windowId));
        // The window type to use for display. Contained in the minecraft:menu regisry; see Inventory for the different values.
        packet.write(ByteUtils.encodeVarInt(windowType));
        // The title of the window
        packet.write(ByteUtils.encodeChat(windowTitle));
        return packet.toByteArray();
    }
    @Override
    public void decode(ByteArrayDataInput in) throws Exception {
        // Packet ID
        packetId = StreamUtils.readVarInt(in);
        // A unique id number for the window to be displayed. Notchian server implementation is a counter, starting at 1.
        windowId = StreamUtils.readVarInt(in);
        // The window type to use for display. Contained in the minecraft:menu regisry; see Inventory for the different values.
        windowType = StreamUtils.readVarInt(in);
        // The title of the window
        windowTitle = StreamUtils.readChat(in);
    }
    public static class Play_Client_OpenWindow_Builder {
        private Play_Client_OpenWindow object = new Play_Client_OpenWindow();
        /** Packet ID */
        public Play_Client_OpenWindow_Builder packetId(int packetId) {
            object.packetId = packetId;
            return this;
        }
        /** A unique id number for the window to be displayed. Notchian server implementation is a counter, starting at 1. */
        public Play_Client_OpenWindow_Builder windowId(int windowId) {
            object.windowId = windowId;
            return this;
        }
        /** The window type to use for display. Contained in the minecraft:menu regisry; see Inventory for the different values. */
        public Play_Client_OpenWindow_Builder windowType(int windowType) {
            object.windowType = windowType;
            return this;
        }
        /** The title of the window */
        public Play_Client_OpenWindow_Builder windowTitle(String windowTitle) {
            object.windowTitle = windowTitle;
            return this;
        }
        public Play_Client_OpenWindow build() {
             return object;
        }

    }
    public static Play_Client_OpenWindow_Builder builder() {
        return new Play_Client_OpenWindow_Builder();
    }
}