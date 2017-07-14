package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x46)
public class SoundEffectEvent extends ServerEvent {
    private int soundId;
    private int soundCategory;
    private int effectPositionX;
    private int effectPositionY;
    private int effectPositionZ;
    private float volume;
    private float pitch;

    public int getSoundId() {
        return soundId;
    }

    public void setSoundId(int soundId) {
        this.soundId = soundId;
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

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setSoundId(lmis.readVarInt());
        setSoundCategory(lmis.readVarInt());
        setEffectPositionX(lmis.readInt());
        setEffectPositionY(lmis.readInt());
        setEffectPositionZ(lmis.readInt());
        setVolume(lmis.readFloat());
        setPitch(lmis.readFloat());
    }

    public double getAbsoluteEffectPositionX() {
        return (double) effectPositionX / 32.0;
    }

    public double getAbsoluteEffectPositionY() {
        return (double) effectPositionY / 32.0;
    }

    public double getAbsoluteEffectPositionZ() {
        return (double) effectPositionZ / 32.0;
    }

}
