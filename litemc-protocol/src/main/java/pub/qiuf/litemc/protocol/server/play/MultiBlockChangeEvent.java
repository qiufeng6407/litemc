package pub.qiuf.litemc.protocol.server.play;

import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.stream.LiteMcInputStream;

public class MultiBlockChangeEvent extends ServerEvent {

    private int chunkX;
    private int chunkZ;
    private int recordCount;

    private long[][] locations;
    private int[] blockIds;

    public int getChunkX() {
        return chunkX;
    }

    public void setChunkX(int chunkX) {
        this.chunkX = chunkX;
    }

    public int getChunkZ() {
        return chunkZ;
    }

    public void setChunkZ(int chunkZ) {
        this.chunkZ = chunkZ;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public long[][] getLocations() {
        return locations;
    }

    public void setLocations(long[][] locations) {
        this.locations = locations;
    }

    public int[] getBlockIds() {
        return blockIds;
    }

    public void setBlockIds(int[] blockIds) {
        this.blockIds = blockIds;
    }

    @Override
    public void decode(LiteMcInputStream lmis) throws Exception {
        setChunkX(lmis.readInt());
        setChunkZ(lmis.readInt());
        setRecordCount(lmis.readVarInt());
        locations = new long[recordCount][3];
        blockIds = new int[recordCount];
        for (int i = 0; i < recordCount; i++) {
            int horizontalPosition = lmis.readUnsignedByte();
            blockIds[i] = lmis.readVarInt();
            locations[i][0] = (horizontalPosition >> 4 & 15) + (chunkX * 16);
            locations[i][1] = lmis.readUnsignedByte();
            locations[i][2] = (horizontalPosition & 15) + (chunkZ * 16);
        }
    }

}
