package pub.qiuf.litemc.protocol.client.handshake;

import pub.qiuf.litemc.common.protocol.ClientEvent;
import pub.qiuf.litemc.common.util.CommUtils;
import pub.qiuf.litemc.common.util.VarUtils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class HandshakeEvent extends ClientEvent {

    private int protocolVersion;
    private String serverAddress;
    private short serverPort;
    private int nextState;

    public HandshakeEvent(int protocolVersion, String serverAddress, short serverPort, int nextState) {
        super(0x00);
        this.protocolVersion = protocolVersion;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.nextState = nextState;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public short getServerPort() {
        return serverPort;
    }

    public void setServerPort(short serverPort) {
        this.serverPort = serverPort;
    }

    public int getNextState() {
        return nextState;
    }

    public void setNextState(int nextState) {
        this.nextState = nextState;
    }

    @Override
    public byte[] encode() throws Exception {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        packet.write(VarUtils.encodeVarInt(packetId));
        packet.write(VarUtils.encodeVarInt(protocolVersion));
        packet.write(CommUtils.encodeString(serverAddress));
        packet.writeShort(serverPort);
        packet.write(VarUtils.encodeVarInt(nextState));
        return packet.toByteArray();
    }

}
