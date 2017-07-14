package pub.qiuf.litemc.common.facade;

public interface AttributeHolder {
    Object attribute(String key, Object value) throws Exception;

    Object attribute(String key) throws Exception;

    <T> T attribute(String key, Class<T> clz) throws Exception;

    <T> T attribute(String key, Class<T> clz, T defaultValue) throws Exception;

    Object remove(String key) throws Exception;

    void clear() throws Exception;
}
