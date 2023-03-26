package ltd.highsoft.frameworks.domain.core;

public class StringFieldType extends FieldType<String> {

    public static StringFieldType asString() {
        return new StringFieldType();
    }

    public StringFieldType nullToEmpty() {
        setNullHandler(path -> "");
        return this;
    }

    @Override
    protected boolean match(Class<?> underlyingType) {
        return String.class.isAssignableFrom(underlyingType);
    }

    @Override
    protected String convert(Object value, String path) {
        return (String) value;
    }

}
