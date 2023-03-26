package ltd.highsoft.frameworks.domain.core;

import static ltd.highsoft.frameworks.domain.core.Remarks.remarks;

public class RemarksFieldType extends FieldType<Remarks> {

    public static RemarksFieldType asRemarks() {
        return new RemarksFieldType();
    }

    @Override
    protected boolean match(Class<?> underlyingType) {
        return String.class.isAssignableFrom(underlyingType);
    }

    @Override
    protected Remarks convert(Object value, String path) {
        return remarks((String) value);
    }

}
