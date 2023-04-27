package ltd.highsoft.hare.frameworks.domain.core;

import java.util.Map;

public class MapFieldType extends FieldType<Map<?, ?>> {

    public static FieldType<Map<?, ?>> asMap() {
        return new MapFieldType();
    }

    @Override
    protected boolean match(Class<?> underlyingType) {
        return Map.class.isAssignableFrom(underlyingType);
    }

    @Override
    protected Map<?, ?> convert(Object value, String path) {
        return (Map<?, ?>) value;
    }

}
