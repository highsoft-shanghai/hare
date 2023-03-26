package ltd.highsoft.frameworks.domain.core;

import static ltd.highsoft.frameworks.domain.core.Code.code;

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
        return code((String) value);
    }

}
