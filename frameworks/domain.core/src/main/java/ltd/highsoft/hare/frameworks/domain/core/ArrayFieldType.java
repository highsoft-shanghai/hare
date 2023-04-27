package ltd.highsoft.hare.frameworks.domain.core;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayFieldType<T> extends FieldType<List<T>> {

    private final FieldType<T> elementType;

    public ArrayFieldType(FieldType<T> elementType) {
        this.elementType = elementType;
    }

    public ArrayFieldType<T> nullToEmpty() {
        setNullHandler(path -> Collections.emptyList());
        return this;
    }

    @Override
    protected boolean match(Class<?> underlyingType) {
        return List.class.isAssignableFrom(underlyingType);
    }

    @Override
    protected List<T> convert(Object value, String path) {
        return ((List<?>) value).stream().map(v -> elementType.from(v, "")).collect(Collectors.toList());
    }

}
