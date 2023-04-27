package ltd.highsoft.hare.frameworks.domain.core;

public class IntFieldType extends FieldType<Integer> {

    public static IntFieldType asInt() {
        return new IntFieldType();
    }

    @Override
    protected boolean match(Class<?> underlyingType) {
        return Number.class.isAssignableFrom(underlyingType);
    }

    @Override
    protected Integer convert(Object value, String path) {
        return ((Number) value).intValue();
    }

}
