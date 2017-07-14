package pub.qiuf.litemc.app;

import java.lang.reflect.Constructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.common.bean.ServerInfo;
import pub.qiuf.litemc.common.facade.Command;
import pub.qiuf.litemc.core.network.MineCraftClient;

import com.google.common.base.Preconditions;

public class Application {
    protected static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        Thread.currentThread().setName("Main");
        if (args == null || args.length < 4) {
            logger.error("error startup arg length");
            System.exit(1);
        }

        String playerName = Preconditions.checkNotNull(args[0]);
        String serverAddress = Preconditions.checkNotNull(args[1]);
        short serverPort = Short.parseShort(Preconditions.checkNotNull(args[2]));
        int protocolVersion = Integer.parseInt(Preconditions.checkNotNull(args[3]));
        String packageName = Preconditions.checkNotNull(args[0]);

        logger.info("-------------------------------------------");
        logger.info("   Player Name: {}", playerName);
        logger.info("Server Address: {}", serverAddress);
        logger.info("   Server Port: {}", serverPort);
        logger.info("       Version: {}", protocolVersion);
        logger.info("-------------------------------------------");

        ServerInfo serverInfo = new ServerInfo(protocolVersion, serverAddress, serverPort);
        MineCraftClient mineCraftClient = new MineCraftClient(playerName, serverInfo);

        String className = String.format("pub.qiuf.litemc.app.%s.Player_%s", packageName, playerName);
        Class<?> clz = Class.forName(className);
        Constructor<?> constructor = clz.getConstructor(MineCraftClient.class);

        Command command = (Command) constructor.newInstance(mineCraftClient);
        mineCraftClient.registerServerEventObserver(command);
        mineCraftClient.setCommand(command);
        mineCraftClient.start();
    }
}
