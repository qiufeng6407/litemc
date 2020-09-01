package pub.qiuf.litemc.app;

import java.lang.reflect.Constructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.common.bean.ServerInfo;
import pub.qiuf.litemc.core.context.GameContext;
import pub.qiuf.litemc.core.network.MineCraftClient;

import com.google.common.base.Preconditions;

public class Application {
    public static void main(String[] args) throws Exception {
        GameContext gameContext = new GameContext("127.0.0.1", 25565, "Robot", 578);
        gameContext.start();
    }
}
