package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x0B)
public class BlockChangeEvent extends ServerEvent {

    private long[] location;
    private int blockId;

    public long[] getLocation() {
        return location;
    }

    public void setLocation(long[] location) {
        this.location = location;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setLocation(lmis.readPosition());
        setBlockId(lmis.readVarInt());
    }

}
