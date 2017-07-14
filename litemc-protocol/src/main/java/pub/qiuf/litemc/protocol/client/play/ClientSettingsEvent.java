package pub.qiuf.litemc.protocol.client.play;

import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.CommUtils;
import pub.qiuf.litemc.common.util.VarUtils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ClientSettingsEvent extends ClientEvent {
    private String locale;
    private byte viewDistance;
    private int chatMode;
    private boolean chatColors;
    private byte displayedSkinParts;
    private int mainHand;

    public ClientSettingsEvent(String locale, byte viewDistance, int chatMode, boolean chatColors, byte displayedSkinParts, int mainHand) {
        super(0x04);
        this.locale = locale;
        this.viewDistance = viewDistance;
        this.chatMode = chatMode;
        this.chatColors = chatColors;
        this.displayedSkinParts = displayedSkinParts;
        this.mainHand = mainHand;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public byte getViewDistance() {
        return viewDistance;
    }

    public void setViewDistance(byte viewDistance) {
        this.viewDistance = viewDistance;
    }

    public int getChatMode() {
        return chatMode;
    }

    public void setChatMode(int chatMode) {
        this.chatMode = chatMode;
    }

    public boolean isChatColors() {
        return chatColors;
    }

    public void setChatColors(boolean chatColors) {
        this.chatColors = chatColors;
    }

    public byte getDisplayedSkinParts() {
        return displayedSkinParts;
    }

    public void setDisplayedSkinParts(byte displayedSkinParts) {
        this.displayedSkinParts = displayedSkinParts;
    }

    public int getMainHand() {
        return mainHand;
    }

    public void setMainHand(int mainHand) {
        this.mainHand = mainHand;
    }

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(packetId));
        packet.write(CommUtils.encodeString(locale));
        packet.writeByte(viewDistance);
        packet.write(VarUtils.encodeVarInt(chatMode));
        packet.writeBoolean(chatColors);
        packet.writeByte(displayedSkinParts);
        packet.write(VarUtils.encodeVarInt(mainHand));
        return packet.toByteArray();
    }

}
