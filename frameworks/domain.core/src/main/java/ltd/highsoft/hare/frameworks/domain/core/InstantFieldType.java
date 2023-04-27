package ltd.highsoft.hare.frameworks.domain.core;

import java.time.Instant;

public class InstantFieldType extends FieldType<Instant> {

    public static InstantFieldType asInstant() {
        return new InstantFieldType();
    }

    @Override
    protected boolean match(Class<?> underlyingType) {
        return String.class.isAssignableFrom(underlyingType);
    }

    @Override
    protected Instant convert(Object value, String path) {
        return Instant.parse((String) value);
    }

}
