package ltd.highsoft.hare.frameworks.context.core;

import ltd.highsoft.hare.frameworks.domain.core.Identity;
import org.junit.jupiter.api.Test;

import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleUserContextTest {

    public static final Identity USER_ACCOUNT = identity("a", "A");
    public static final Identity USER = identity("u", "U");
    public static final Identity TENANT = identity("t", "T");

    @Test
    void should_answer_yes_when_test_for_validity() {
        assertThat(new SimpleUserContext(USER_ACCOUNT, USER, TENANT).valid()).isTrue();
    }

    @Test
    void should_be_able_to_hold_user_account() {
        assertThat(new SimpleUserContext(USER_ACCOUNT, USER, TENANT).userAccount()).isEqualTo(USER_ACCOUNT);
    }

    @Test
    void should_be_able_to_hold_user() {
        assertThat(new SimpleUserContext(USER_ACCOUNT, USER, TENANT).user()).isEqualTo(USER);
    }

    @Test
    void should_be_able_to_hold_tenant() {
        assertThat(new SimpleUserContext(USER_ACCOUNT, USER, TENANT).tenant()).isEqualTo(TENANT);
    }

}
