package pub.qiuf.litemc.core.network.codec;

import java.util.HashMap;
import java.util.Map;

import com.google.common.io.ByteStreams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.bean.datatype.Packet;
import pub.qiuf.litemc.common.util.ByteUtils;
import pub.qiuf.litemc.common.util.VarUtils;
import pub.qiuf.litemc.core.context.GameContext;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.reflect.ClassPath;

public class ClientEventBuilder {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final GameContext gameCtx;

    public ClientEventBuilder(GameContext gameCtx) {
        Preconditions.checkNotNull(gameCtx);
        this.gameCtx = gameCtx;
    }

    private final Map<Integer, Class<?>> statusPacketClassMap = new HashMap<>();
    private final Map<Integer, Class<?>> handshakePacketClassMap = new HashMap<>();
    private final Map<Integer, Class<?>> loginPacketClassMap = new HashMap<>();
    private final Map<Integer, Class<?>> playPacketClassMap = new HashMap<>();

    protected String getStatusEventClassPackageName() {
        return "pub.qiuf.litemc.protocol.status.client";
    }

    protected String getHandshakeEventClassPackageName() {
        return "pub.qiuf.litemc.protocol.handshake.client";
    }

    protected String getLoginEventClassPackageName() {
        return "pub.qiuf.litemc.protocol.login.client";
    }

    protected String getPlayEventClassPackageName() {
        return "pub.qiuf.litemc.protocol.play.client";
    }

    public void loadEventClass() throws Exception {
        logger.info("loading EventClass");
        ClassPath classpath = ClassPath.from(getClass().getClassLoader());
        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses(getStatusEventClassPackageName())) {
            Class<?> clz = Class.forName(classInfo.getName());
            ServerPacket serverPacket = clz.getAnnotation(ServerPacket.class);
            if (serverPacket == null) {
                continue;
            }
            logger.debug("Loading {}", classInfo.getName());
            statusPacketClassMap.put(serverPacket.value(), clz);
        }

        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses(getHandshakeEventClassPackageName())) {
            Class<?> clz = Class.forName(classInfo.getName());
            ServerPacket serverPacket = clz.getAnnotation(ServerPacket.class);
            if (serverPacket == null) {
                continue;
            }
            logger.debug("Loading {}", classInfo.getName());
            handshakePacketClassMap.put(serverPacket.value(), clz);
        }

        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses(getLoginEventClassPackageName())) {
            Class<?> clz = Class.forName(classInfo.getName());
            ServerPacket serverPacket = clz.getAnnotation(ServerPacket.class);
            if (serverPacket == null) {
                continue;
            }
            logger.debug("Loading {}", classInfo.getName());
            loginPacketClassMap.put(serverPacket.value(), clz);
        }

        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses(getPlayEventClassPackageName())) {
            Class<?> clz = Class.forName(classInfo.getName());
            ServerPacket serverPacket = clz.getAnnotation(ServerPacket.class);
            if (serverPacket == null) {
                continue;
            }
            logger.debug("Loading {}", classInfo.getName());
            playPacketClassMap.put(serverPacket.value(), clz);
        }
    }

    public Optional<Packet> build(byte[] packet) throws Exception {
        int packetId = VarUtils.decodeVarInt(packet);
        logger.debug("Build: [{}] [{}]", StringUtils.leftPad(Integer.toHexString(packetId), 2, '0'), ByteUtils.bytestoHexString(packet));
        Map<Integer, Class<?>> packetClassMap = null;
        switch (gameCtx.getClientState()) {
        case STATUS:
            packetClassMap = statusPacketClassMap;
            break;
        case HANDSHAKE:
            packetClassMap = handshakePacketClassMap;
            break;
        case LOGIN:
            packetClassMap = loginPacketClassMap;
            break;
        case PLAY:
            packetClassMap = playPacketClassMap;
            break;
        }
        Preconditions.checkNotNull(packetClassMap);
        if (!packetClassMap.containsKey(packetId)) {
            return Optional.absent();
        }
        Class<?> clz = packetClassMap.get(packetId);

        String packetSeg = ByteUtils.bytestoHexString(packet);
        if (packetSeg.length() > 40) {
            packetSeg  = packetSeg.substring(0, 40);
        }
        logger.debug("decode: {} - [{}]", clz.getSimpleName(), packetSeg);

        try {
            Packet event = (Packet) clz.newInstance();
            event.decode(ByteStreams.newDataInput(packet));
            return Optional.of(event);
        } catch (Exception e) {
            return Optional.absent();
        }
    }
}
