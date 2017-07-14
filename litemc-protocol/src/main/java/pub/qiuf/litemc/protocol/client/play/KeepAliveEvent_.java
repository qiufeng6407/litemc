package pub.qiuf.litemc.protocol.client.play;

import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.VarUtils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class KeepAliveEvent_ extends ClientEvent {

    private int keepAliveId;

    public KeepAliveEvent_(int keepAliveId) {
        super(0x0B);
        this.keepAliveId = keepAliveId;
    }

    public int getKeepAliveId() {
        return keepAliveId;
    }

    public void setKeepAliveId(int keepAliveId) {
        this.keepAliveId = keepAliveId;
    }

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(packetId));
        packet.write(VarUtils.encodeVarInt(keepAliveId));
        return packet.toByteArray();
    }

}
