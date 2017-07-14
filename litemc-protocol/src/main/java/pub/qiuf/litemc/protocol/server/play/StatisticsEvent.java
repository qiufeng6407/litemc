package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x07)
public class StatisticsEvent extends ServerEvent {

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
    }

}
