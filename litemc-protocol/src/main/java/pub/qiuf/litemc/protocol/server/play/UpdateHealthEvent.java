package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x3e)
public class UpdateHealthEvent extends ServerEvent {

    private float health;
    private int food;
    private float foodSaturation;

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public float getFoodSaturation() {
        return foodSaturation;
    }

    public void setFoodSaturation(float foodSaturation) {
        this.foodSaturation = foodSaturation;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setHealth(lmis.readFloat());
        setFood(lmis.readVarInt());
        setFoodSaturation(lmis.readFloat());
    }

}
