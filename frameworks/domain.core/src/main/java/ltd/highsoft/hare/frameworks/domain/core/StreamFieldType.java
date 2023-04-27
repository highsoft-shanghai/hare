package ltd.highsoft.hare.frameworks.domain.core;

import java.util.List;
import java.util.stream.Stream;

public class StreamFieldType<T> extends FieldType<Stream<T>> {

    private final FieldType<T> elementType;

    public StreamFieldType(FieldType<T> elementType) {
        this.elementType = elementType;
    }

    public StreamFieldType<T> nullToEmpty() {
        setNullHandler(path -> Stream.empty());
        return this;
    }

    @Override
    protected boolean match(Class<?> underlyingType) {
        return List.class.isAssignableFrom(underlyingType);
    }

    @Override
    protected Stream<T> convert(Object value, String path) {
        return ((List<?>) value).stream().map(v -> elementType.from(v, ""));
    }

}
