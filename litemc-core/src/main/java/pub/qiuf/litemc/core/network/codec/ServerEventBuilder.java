package pub.qiuf.litemc.core.network.codec;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.qiuf.litemc.common.annotation.ServerPacket;
import pub.qiuf.litemc.common.protocol.ServerEvent;
import pub.qiuf.litemc.common.util.ByteUtils;
import pub.qiuf.litemc.common.util.VarUtils;
import pub.qiuf.litemc.core.network.MineCraftClient;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.reflect.ClassPath;

public class ServerEventBuilder {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final MineCraftClient mineCraftClient;

    public ServerEventBuilder(MineCraftClient mineCraftClient) {
        Preconditions.checkNotNull(mineCraftClient);
        this.mineCraftClient = mineCraftClient;
    }

    private final Map<Integer, Class<?>> statusPacketClassMap = new HashMap<>();
    private final Map<Integer, Class<?>> handshakePacketClassMap = new HashMap<>();
    private final Map<Integer, Class<?>> loginPacketClassMap = new HashMap<>();
    private final Map<Integer, Class<?>> playPacketClassMap = new HashMap<>();

    protected String getStatusEventClassPackageName() {
        return "pub.qiuf.litemc.protocol.server.status";
    }

    protected String getHandshakeEventClassPackageName() {
        return "pub.qiuf.litemc.protocol.server.handshake";
    }

    protected String getLoginEventClassPackageName() {
        return "pub.qiuf.litemc.protocol.server.login";
    }

    protected String getPlayEventClassPackageName() {
        return "pub.qiuf.litemc.protocol.server.play";
    }

    public void loadEventClass() throws Exception {
        ClassPath classpath = ClassPath.from(getClass().getClassLoader());
        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses(getStatusEventClassPackageName())) {
            Class<?> clz = Class.forName(classInfo.getName());
            ServerPacket serverPacket = clz.getAnnotation(ServerPacket.class);
            if (serverPacket == null) {
                continue;
            }
            statusPacketClassMap.put(serverPacket.value(), clz);
        }

        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses(getHandshakeEventClassPackageName())) {
            Class<?> clz = Class.forName(classInfo.getName());
            ServerPacket serverPacket = clz.getAnnotation(ServerPacket.class);
            if (serverPacket == null) {
                continue;
            }
            handshakePacketClassMap.put(serverPacket.value(), clz);
        }

        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses(getLoginEventClassPackageName())) {
            Class<?> clz = Class.forName(classInfo.getName());
            ServerPacket serverPacket = clz.getAnnotation(ServerPacket.class);
            if (serverPacket == null) {
                continue;
            }
            loginPacketClassMap.put(serverPacket.value(), clz);
        }

        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses(getPlayEventClassPackageName())) {
            Class<?> clz = Class.forName(classInfo.getName());
            ServerPacket serverPacket = clz.getAnnotation(ServerPacket.class);
            if (serverPacket == null) {
                continue;
            }
            playPacketClassMap.put(serverPacket.value(), clz);
        }
    }

    public Optional<ServerEvent> build(byte[] packet) throws Exception {
        int packetId = VarUtils.decodeVarInt(packet);
        Map<Integer, Class<?>> packetClassMap = null;
        switch (mineCraftClient.getClientState()) {
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
        try {
            ServerEvent event = (ServerEvent) clz.newInstance();
            event.decode(packet);
            return Optional.of(event);
        } catch (Exception e) {
            logger.error("decode error: {} - {}", clz.getSimpleName(), ByteUtils.bytestoHexString(packet));
            return Optional.absent();
        }
    }

}
