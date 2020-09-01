package pub.qiuf.litemc.core.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.core.context.GameContext;
import pub.qiuf.litemc.core.network.MineCraftClient;

public class ChunkManager {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final GameContext gameCtx;

    public ChunkManager(GameContext gameCtx) {
        this.gameCtx = gameCtx;
    }

}
