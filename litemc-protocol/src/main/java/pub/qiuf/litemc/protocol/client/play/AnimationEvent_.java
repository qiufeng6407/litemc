package pub.qiuf.litemc.protocol.client.play;

import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.VarUtils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class AnimationEvent_ extends ClientEvent {
    private int hand;

    public AnimationEvent_(int hand) {
        super(0x1A);
        this.hand = hand;
    }

    public int getHand() {
        return hand;
    }

    public void setHand(int hand) {
        this.hand = hand;
    }

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(packetId));
        packet.write(VarUtils.encodeVarInt(hand));
        return packet.toByteArray();
    }

}
