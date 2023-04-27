package ltd.highsoft.hare.frameworks.domain.core;

import java.util.*;

public class MapObjectSink implements ObjectSink {

    private final Map<String, Object> values = new LinkedHashMap<>();

    public Map<String, Object> values() {
        return values;
    }

    @Override
    public void put(String key, Object value) {
        if (values.containsKey(key)) throw new BadInputException(I18nMessage.message("error.duplicated-key-detected", key));
        values.put(key, value);
    }

    @Override
    public ObjectSink putObject(String key) {
        var nested = new MapObjectSink();
        put(key, nested.values());
        return nested;
    }

    @Override
    public ValueSink putArray(String key) {
        var array = new ListValueSink();
        put(key, array.values());
        return array;
    }

    @Override
    public Map<String, Object> toMap() {
        return Collections.unmodifiableMap(values);
    }

}
