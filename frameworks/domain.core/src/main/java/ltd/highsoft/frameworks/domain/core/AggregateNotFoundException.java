package ltd.highsoft.frameworks.domain.core;

import static ltd.highsoft.frameworks.domain.core.I18nMessage.message;

public class AggregateNotFoundException extends DomainException {

    public static final String MESSAGE_CODE = "error.aggregate-not-found";

    public AggregateNotFoundException(I18nMessage message) {
        super(message);
    }

    public AggregateNotFoundException(Object... data) {
        this(message(MESSAGE_CODE, data));
    }

    public AggregateNotFoundException(Throwable cause, Object... data) {
        super(cause, message(MESSAGE_CODE, data));
    }

}
