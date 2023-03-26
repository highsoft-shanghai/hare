package ltd.highsoft.frameworks.domain.core;

import java.util.Map;

import static ltd.highsoft.frameworks.domain.core.Payload.payload;

public class ObjectFieldType extends FieldType<Payload> {

    public static ObjectFieldType asObject() {
        return new ObjectFieldType();
    }

    public ObjectFieldType nullToEmpty() {
        setNullHandler(path -> Payload.EMPTY);
        return this;
    }

    @Override
    protected boolean match(Class<?> underlyingType) {
        return Map.class.isAssignableFrom(underlyingType);
    }

    @Override
    protected Payload convert(Object value, String path) {
        return payload(value, path);
    }

}
