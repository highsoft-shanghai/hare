package ltd.highsoft.frameworks.domain.core;

import java.math.BigDecimal;

public class DecimalFieldType extends FieldType<BigDecimal> {

    public static DecimalFieldType asDecimal() {
        return new DecimalFieldType();
    }

    @Override
    protected boolean match(Class<?> underlyingType) {
        return Number.class.isAssignableFrom(underlyingType) || String.class.isAssignableFrom(underlyingType);
    }

    @Override
    protected BigDecimal convert(Object value, String path) {
        if (Number.class.isAssignableFrom(value.getClass())) return BigDecimal.valueOf(((Number) value).doubleValue());
        return new BigDecimal((String) value);
    }

}
