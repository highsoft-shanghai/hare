package ltd.highsoft.frameworks.domain.core;

import static ltd.highsoft.frameworks.domain.core.I18nMessage.message;

public class DuplicateAggregateDetectedException extends DomainException {

    public static final String MESSAGE_CODE = "error.duplicate-aggregate-detected";

    public DuplicateAggregateDetectedException(I18nMessage message) {
        super(message);
    }

    public DuplicateAggregateDetectedException(Object... data) {
        this(message(MESSAGE_CODE, data));
    }

}
