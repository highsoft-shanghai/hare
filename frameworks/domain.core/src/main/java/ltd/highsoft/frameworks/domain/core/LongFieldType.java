package ltd.highsoft.frameworks.domain.core;

public class LongFieldType extends FieldType<Long> {

    public static FieldType<Long> asLong() {
        return new LongFieldType();
    }

    @Override
    protected boolean match(Class<?> underlyingType) {
        return Number.class.isAssignableFrom(underlyingType);
    }

    @Override
    protected Long convert(Object value, String path) {
        return ((Number) value).longValue();
    }

}
