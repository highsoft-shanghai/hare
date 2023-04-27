package ltd.highsoft.hare.frameworks.application.core;

import ltd.highsoft.hare.frameworks.domain.core.DomainException;
import ltd.highsoft.hare.frameworks.domain.core.MessageResolver;

public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public String format(MessageResolver messageResolver) {
        if (getCause() instanceof DomainException) return ((DomainException) getCause()).format(messageResolver);
        return messageResolver.resolve(getMessage());
    }

    @Override
    public String getMessage() {
        if (getCause() != null) return getCause().getMessage();
        return super.getMessage();
    }

}
