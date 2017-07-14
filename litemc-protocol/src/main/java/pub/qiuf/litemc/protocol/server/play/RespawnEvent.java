package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

@ServerPacket(0x33)
public class RespawnEvent extends ServerEvent {

    private int dimension;
    private int difficulty;
    private int gamemode;
    private String levelType;

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getGamemode() {
        return gamemode;
    }

    public void setGamemode(int gamemode) {
        this.gamemode = gamemode;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setDimension(lmis.readInt());
        setDifficulty(lmis.readUnsignedByte());
        setGamemode(lmis.readUnsignedByte());
        setLevelType(lmis.readString());
    }

}
