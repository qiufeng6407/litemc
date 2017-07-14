package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x0F)
public class ChatMessageEvent extends ServerEvent {

    private String jsonData;
    private byte position;

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public byte getPosition() {
        return position;
    }

    public void setPosition(byte position) {
        this.position = position;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setJsonData(lmis.readString());
        setPosition(lmis.readByte());
    }

}
