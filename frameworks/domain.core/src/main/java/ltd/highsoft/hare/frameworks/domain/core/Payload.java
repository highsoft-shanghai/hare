package ltd.highsoft.hare.frameworks.domain.core;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;

import static ltd.highsoft.hare.frameworks.domain.core.MapFieldType.asMap;

public final class Payload {

    public static final Payload EMPTY = payload(Collections.emptyMap());
    private final Object value;
    private final String path;

    public static Payload payload(Object value) {
        return payload(value, StringUtils.EMPTY);
    }

    public static Payload payload(Object value, String path) {
        return new Payload(value, path);
    }

    public <T> T get(FieldType<T> type) {
        return type.from(value, path);
    }

    public <T> T get(String key, FieldType<T> type) {
        return type.from(get(asMap()).get(key), path + "/" + key);
    }

    public Object value() {
        return value;
    }


    private Payload(Object value, String path) {
        this.value = value;
        this.path = path;
    }

}
