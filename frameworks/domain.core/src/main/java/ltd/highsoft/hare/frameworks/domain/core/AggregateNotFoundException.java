package ltd.highsoft.hare.frameworks.domain.core;

public class AggregateNotFoundException extends DomainException {

    public static final String MESSAGE_CODE = "error.aggregate-not-found";

    public AggregateNotFoundException(I18nMessage message) {
        super(message);
    }

    public AggregateNotFoundException(Object... data) {
        this(I18nMessage.message(MESSAGE_CODE, data));
    }

    public AggregateNotFoundException(Throwable cause, Object... data) {
        super(cause, I18nMessage.message(MESSAGE_CODE, data));
    }

}
