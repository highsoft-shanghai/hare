package ltd.highsoft.frameworks.domain.core;

public class BooleanFieldType extends FieldType<Boolean> {

    public static BooleanFieldType asBoolean() {
        return new BooleanFieldType();
    }

    @Override
    protected boolean match(Class<?> underlyingType) {
        return Boolean.class.isAssignableFrom(underlyingType);
    }

    @Override
    protected Boolean convert(Object value, String path) {
        return (Boolean) value;
    }

}
