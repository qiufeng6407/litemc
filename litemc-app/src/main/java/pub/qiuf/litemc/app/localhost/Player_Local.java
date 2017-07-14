package pub.qiuf.litemc.app.localhost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.common.facade.Command;
import pub.qiuf.litemc.core.network.MineCraftClient;

import com.google.common.base.Preconditions;

public class Player_Local implements Command {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected Command state = null;

    protected final MineCraftClient mineCraftClient;

    public Player_Local(MineCraftClient mineCraftClient) {
        Preconditions.checkNotNull(mineCraftClient);
        this.mineCraftClient = mineCraftClient;
    }

    @Override
    public void execute(long clientTick) throws Exception {
        if (state != null) {
            state.execute(clientTick);
        }
    }
}
