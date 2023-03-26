package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static ltd.highsoft.frameworks.domain.core.I18nMessage.message;
import static org.assertj.core.api.Assertions.assertThat;

class AuthenticationExceptionTest {

    @Test
    void should_be_able_to_represent_authentication_errors() {
        var throwable = new AuthenticationException(message("error-code"));
        assertThat(throwable).isInstanceOf(DomainException.class);
        assertThat(throwable).hasMessage("error-code");
    }

}
