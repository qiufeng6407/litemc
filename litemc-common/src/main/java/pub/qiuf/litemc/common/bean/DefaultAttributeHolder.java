package pub.qiuf.litemc.common.bean;

import java.util.HashMap;
import java.util.Map;

import pub.qiuf.litemc.common.facade.AttributeHolder;

public class DefaultAttributeHolder implements AttributeHolder {
    protected Map<String, Object> attributeMap = new HashMap<>();

    @Override
    public Object attribute(String key, Object value) throws Exception {
        return attributeMap.put(key, value);
    }

    @Override
    public Object attribute(String key) throws Exception {
        return attributeMap.get(key);
    }

    @Override
    public <T> T attribute(String key, Class<T> clz) throws Exception {
        Object value = attribute(key);
        if (value == null) {
            return null;
        }
        return clz.cast(value);
    }

    @Override
    public Object remove(String key) throws Exception {
        if (!attributeMap.containsKey(key)) {
            return null;
        }
        return attributeMap.remove(key);
    }

    @Override
    public <T> T attribute(String key, Class<T> clz, T defaultValue) throws Exception {
        Object value = attribute(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return clz.cast(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Override
    public void clear() throws Exception {
        attributeMap.clear();
    }
}
