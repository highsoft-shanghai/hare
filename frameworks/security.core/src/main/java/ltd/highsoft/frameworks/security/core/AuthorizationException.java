package ltd.highsoft.frameworks.security.core;

import ltd.highsoft.frameworks.domain.core.*;

public class AuthorizationException extends DomainException {

    public AuthorizationException(I18nMessage message) {
        super(message);
    }

}
