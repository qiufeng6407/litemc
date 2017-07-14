package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x18)
public class PluginMessageEvent extends ServerEvent {
    private String channel;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setChannel(lmis.readString());
    }

}
