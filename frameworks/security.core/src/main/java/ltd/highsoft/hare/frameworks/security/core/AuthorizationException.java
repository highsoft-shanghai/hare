package ltd.highsoft.hare.frameworks.security.core;

import ltd.highsoft.hare.frameworks.domain.core.DomainException;
import ltd.highsoft.hare.frameworks.domain.core.I18nMessage;

public class AuthorizationException extends DomainException {

    public AuthorizationException(I18nMessage message) {
        super(message);
    }

}
