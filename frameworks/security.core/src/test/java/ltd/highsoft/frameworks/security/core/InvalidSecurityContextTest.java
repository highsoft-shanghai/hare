package ltd.highsoft.frameworks.security.core;

import ltd.highsoft.frameworks.domain.core.AuthenticationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class InvalidSecurityContextTest {

    @Test
    void should_answer_no_when_test_for_validity() {
        assertThat(new InvalidSecurityContext().valid()).isFalse();
    }

    @Test
    void should_report_error_when_try_to_get_token() {
        assertThatThrownBy(() -> new InvalidSecurityContext().token()).isInstanceOf(AuthenticationException.class).hasMessage("error.bad-token");
    }

    @Test
    void should_report_error_when_try_to_get_authorities() {
        assertThatThrownBy(() -> new InvalidSecurityContext().grantedAuthorities()).isInstanceOf(AuthenticationException.class).hasMessage("error.bad-token");
    }

    @Test
    void should_report_error_when_try_to_authorize_requests() {
        assertThatThrownBy(() -> new InvalidSecurityContext().authorize(RequiredAuthorities.ANONYMOUS)).isInstanceOf(AuthenticationException.class).hasMessage("error.bad-token");
    }

}
