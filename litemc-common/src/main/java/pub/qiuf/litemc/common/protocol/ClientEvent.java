package pub.qiuf.litemc.common.protocol;

public abstract class ClientEvent {
    protected int packetId;

    public ClientEvent(int packetId) {
        this.packetId = packetId;
    }

    public int getPacketId() {
        return packetId;
    }

    public void setPacketId(int packetId) {
        this.packetId = packetId;
    }

    public abstract byte[] encode() throws Exception;

}
