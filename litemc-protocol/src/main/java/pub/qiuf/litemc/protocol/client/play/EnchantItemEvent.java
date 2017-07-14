package pub.qiuf.litemc.protocol.client.play;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.VarUtils;

public class EnchantItemEvent extends ClientEvent {

    private short windowId;
    private byte enchantment;

    public EnchantItemEvent(short windowId, byte enchantment) {
        super(0x06);
        this.windowId = windowId;
        this.enchantment = enchantment;
    }

    public short getWindowId() {
        return windowId;
    }

    public void setWindowId(short windowId) {
        this.windowId = windowId;
    }

    public byte getEnchantment() {
        return enchantment;
    }

    public void setEnchantment(byte enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(packetId));
        packet.writeByte(enchantment);
        return packet.toByteArray();
    }

}
