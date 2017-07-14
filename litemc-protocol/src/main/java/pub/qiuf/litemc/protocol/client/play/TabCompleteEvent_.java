package pub.qiuf.litemc.protocol.client.play;

import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.CommUtils;
import pub.qiuf.litemc.common.util.VarUtils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class TabCompleteEvent_ extends ClientEvent {

    private String text;
    private boolean assumeCommand;
    private boolean hasPosition;
    private long[] lookedAtBlock;

    public TabCompleteEvent_(String text, boolean assumeCommand, boolean hasPosition, long[] lookedAtBlock) {
        super(0x01);
        this.text = text;
        this.assumeCommand = assumeCommand;
        this.hasPosition = hasPosition;
        this.lookedAtBlock = lookedAtBlock;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isAssumeCommand() {
        return assumeCommand;
    }

    public void setAssumeCommand(boolean assumeCommand) {
        this.assumeCommand = assumeCommand;
    }

    public boolean isHasPosition() {
        return hasPosition;
    }

    public void setHasPosition(boolean hasPosition) {
        this.hasPosition = hasPosition;
    }

    public long[] getLookedAtBlock() {
        return lookedAtBlock;
    }

    public void setLookedAtBlock(long[] lookedAtBlock) {
        this.lookedAtBlock = lookedAtBlock;
    }

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(packetId));
        packet.write(CommUtils.encodeString(text));
        packet.writeBoolean(assumeCommand);
        packet.writeBoolean(hasPosition);
        if (hasPosition) {
            packet.write(CommUtils.encodePosition(lookedAtBlock));
        }
        return packet.toByteArray();
    }

}
