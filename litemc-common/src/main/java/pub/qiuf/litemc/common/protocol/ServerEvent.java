package pub.qiuf.litemc.common.protocol;

import java.io.ByteArrayInputStream;

import pub.qiuf.litemc.common.stream.LiteMcInputStream;

public abstract class ServerEvent {
    protected int packetId;

    public int getPacketId() {
        return packetId;
    }

    public void setPacketId(int packetId) {
        this.packetId = packetId;
    }

    public void decode(byte[] packetData) throws Exception {
        LiteMcInputStream lmis = null;
        try {
            lmis = new LiteMcInputStream(new ByteArrayInputStream(packetData));
            setPacketId(lmis.readVarInt());
            decode(lmis);
        } finally {
            if (lmis != null) {
                lmis.close();
            }
        }
    }

    public abstract void decode(LiteMcInputStream lmis) throws Exception;

}
