package ltd.highsoft.hare.frameworks.domain.core;

import java.util.List;

public class DomainException extends RuntimeException {

    private static final MessageResolver MESSAGE_RESOLVER = new SimpleMessageResolver();
    private static final String ERROR_DOMAIN_ERROR = "error.domain-error";
    private final I18nMessage message;

    public DomainException() {
        this.message = I18nMessage.message(ERROR_DOMAIN_ERROR);
    }

    public DomainException(I18nMessage message) {
        this.message = message;
    }

    public DomainException(Throwable cause) {
        super(cause);
        this.message = I18nMessage.message(ERROR_DOMAIN_ERROR);
    }

    public DomainException(Throwable cause, I18nMessage message) {
        super(cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message.format(MESSAGE_RESOLVER);
    }

    public String format(MessageResolver resolver) {
        return message.format(resolver);
    }

    public List<Object> data() {
        return message.data();
    }

}
