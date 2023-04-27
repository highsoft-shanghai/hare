package ltd.highsoft.hare.frameworks.domain.core;

public class CodeFieldType extends FieldType<Code> {

    public static CodeFieldType asCode() {
        return new CodeFieldType();
    }

    @Override
    protected boolean match(Class<?> underlyingType) {
        return String.class.isAssignableFrom(underlyingType);
    }

    @Override
    protected Code convert(Object value, String path) {
        return Code.code((String) value);
    }

}
