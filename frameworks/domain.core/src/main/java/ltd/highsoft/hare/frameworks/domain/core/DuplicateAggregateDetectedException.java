package ltd.highsoft.hare.frameworks.domain.core;

public class DuplicateAggregateDetectedException extends DomainException {

    public static final String MESSAGE_CODE = "error.duplicate-aggregate-detected";

    public DuplicateAggregateDetectedException(I18nMessage message) {
        super(message);
    }

    public DuplicateAggregateDetectedException(Object... data) {
        this(I18nMessage.message(MESSAGE_CODE, data));
    }

}
