package pub.qiuf.litemc.core.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;

import pub.qiuf.litemc.core.network.MineCraftClient;
import pub.qiuf.litemc.protocol.server.play.ChunkDataEvent;

public class ChunkManager {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final MineCraftClient mineCraftClient;

    public ChunkManager(MineCraftClient mineCraftClient) {
        this.mineCraftClient = mineCraftClient;
    }

    @Subscribe
    public void onChunkData(ChunkDataEvent event) throws Exception {
        // logger.info("downloading terrian");
    }
}
