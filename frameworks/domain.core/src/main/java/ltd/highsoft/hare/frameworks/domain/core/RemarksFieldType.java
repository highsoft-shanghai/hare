package ltd.highsoft.hare.frameworks.domain.core;

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
        return Remarks.remarks((String) value);
    }

}
