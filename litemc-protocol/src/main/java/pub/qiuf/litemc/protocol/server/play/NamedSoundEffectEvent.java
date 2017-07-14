package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x19)
public class NamedSoundEffectEvent extends ServerEvent {
    private String soundName;
    private int soundCategory;
    private int effectPositionX;
    private int effectPositionY;
    private int effectPositionZ;
    private float volume;
    private float pitch;

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    public int getSoundCategory() {
        return soundCategory;
    }

    public void setSoundCategory(int soundCategory) {
        this.soundCategory = soundCategory;
    }

    public int getEffectPositionX() {
        return effectPositionX;
    }

    public void setEffectPositionX(int effectPositionX) {
        this.effectPositionX = effectPositionX;
    }

    public int getEffectPositionY() {
        return effectPositionY;
    }

    public void setEffectPositionY(int effectPositionY) {
        this.effectPositionY = effectPositionY;
    }

    public int getEffectPositionZ() {
        return effectPositionZ;
    }

    public void setEffectPositionZ(int effectPositionZ) {
        this.effectPositionZ = effectPositionZ;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public double getDoubleEffectPositionX() {
        return effectPositionX / 8.0;
    }

    public double getDoubleEffectPositionY() {
        return effectPositionY / 8.0;
    }

    public double getDoubleEffectPositionZ() {
        return effectPositionZ / 8.0;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setSoundName(lmis.readString());
        setSoundCategory(lmis.readVarInt());
        setEffectPositionX(lmis.readInt());
        setEffectPositionY(lmis.readInt());
        setEffectPositionZ(lmis.readInt());
        setVolume(lmis.readFloat());
        setPitch(lmis.readFloat());
    }

}
