package ltd.highsoft.frameworks.security.core;

import ltd.highsoft.frameworks.domain.core.DomainException;
import org.junit.jupiter.api.Test;

import static ltd.highsoft.frameworks.domain.core.I18nMessage.message;
import static org.assertj.core.api.Assertions.assertThat;

class AuthorizationExceptionTest {

    @Test
    void should_provide_clear_error_info() {
        var exception = new AuthorizationException(message("error.access-denied", RequiredAuthorities.of("f2"), GrantedAuthorities.of("f1")));
        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception).hasMessage("error.access-denied: [RequiredAuthorities(f2), GrantedAuthorities(f1)]");
    }

}
