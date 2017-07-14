package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x48)
public class CollectItemEvent extends ServerEvent {

    private int collectedEntityId;
    private int collectorEntityId;
    private int PickupItemCount;

    public int getCollectedEntityId() {
        return collectedEntityId;
    }

    public void setCollectedEntityId(int collectedEntityId) {
        this.collectedEntityId = collectedEntityId;
    }

    public int getCollectorEntityId() {
        return collectorEntityId;
    }

    public void setCollectorEntityId(int collectorEntityId) {
        this.collectorEntityId = collectorEntityId;
    }

    public int getPickupItemCount() {
        return PickupItemCount;
    }

    public void setPickupItemCount(int pickupItemCount) {
        PickupItemCount = pickupItemCount;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setCollectedEntityId(lmis.readVarInt());
        setCollectorEntityId(lmis.readVarInt());
        setPickupItemCount(lmis.readVarInt());
    }

}
