package pub.qiuf.litemc.common.facade;

public interface Command {
    void execute(long clientTick) throws Exception;
}
