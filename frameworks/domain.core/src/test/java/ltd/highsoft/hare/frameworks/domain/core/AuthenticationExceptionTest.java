package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthenticationExceptionTest {

    @Test
    void should_be_able_to_represent_authentication_errors() {
        var throwable = new AuthenticationException(I18nMessage.message("error-code"));
        assertThat(throwable).isInstanceOf(DomainException.class);
        assertThat(throwable).hasMessage("error-code");
    }

}
