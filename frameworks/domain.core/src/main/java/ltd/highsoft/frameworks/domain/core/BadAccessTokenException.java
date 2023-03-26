package ltd.highsoft.frameworks.domain.core;

import static ltd.highsoft.frameworks.domain.core.I18nMessage.message;

public class BadAccessTokenException extends AuthenticationException {

    public BadAccessTokenException() {
        super(message("error.bad-token"));
    }

}
