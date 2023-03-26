package ltd.highsoft.frameworks.domain.core;

import static ltd.highsoft.frameworks.domain.core.Name.name;

public class NameFieldType extends FieldType<Name> {

    public static NameFieldType asName() {
        return new NameFieldType();
    }

    @Override
    protected boolean match(Class<?> underlyingType) {
        return String.class.isAssignableFrom(underlyingType);
    }

    @Override
    protected Name convert(Object value, String path) {
        return name((String) value);
    }

}
