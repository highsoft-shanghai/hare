package ltd.highsoft.frameworks.context.core;

import ltd.highsoft.frameworks.domain.core.AuthenticationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class InvalidUserContextTest {

    @Test
    void should_answer_no_when_test_for_validity() {
        assertThat(new InvalidUserContext().valid()).isFalse();
    }

    @Test
    void should_report_error_when_try_to_get_user_account() {
        assertThatThrownBy(() -> new InvalidUserContext().userAccount()).isInstanceOf(AuthenticationException.class).hasMessage("error.bad-token");
    }

    @Test
    void should_report_error_when_try_to_get_user() {
        assertThatThrownBy(() -> new InvalidUserContext().user()).isInstanceOf(AuthenticationException.class).hasMessage("error.bad-token");
    }

    @Test
    void should_report_error_when_try_to_get_tenant() {
        assertThatThrownBy(() -> new InvalidUserContext().tenant()).isInstanceOf(AuthenticationException.class).hasMessage("error.bad-token");
    }

}
