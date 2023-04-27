package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Identity;
import org.junit.jupiter.api.Test;

import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;
import static org.assertj.core.api.Assertions.assertThat;

class AccessTokenOwnerTest {

    private final Identity ACCOUNT_OF_JOHN = identity("john@highsoft", "John");
    private final Identity JOHN = identity("john", "John");
    private final Identity HIGHSOFT = identity("highsoft", "Highsoft");

    @Test
    void should_be_able_to_hold_user_account() {
        assertThat(new AccessTokenOwner(ACCOUNT_OF_JOHN, JOHN, HIGHSOFT).userAccount()).isEqualTo(ACCOUNT_OF_JOHN);
    }

    @Test
    void should_be_able_to_hold_user() {
        assertThat(new AccessTokenOwner(ACCOUNT_OF_JOHN, JOHN, HIGHSOFT).user()).isEqualTo(JOHN);
    }

    @Test
    void should_be_able_to_hold_tenant() {
        assertThat(new AccessTokenOwner(ACCOUNT_OF_JOHN, JOHN, HIGHSOFT).tenant()).isEqualTo(HIGHSOFT);
    }

    @Test
    void should_be_able_to_compare_to_others_by_contents() {
        assertThat(new AccessTokenOwner(ACCOUNT_OF_JOHN, JOHN, HIGHSOFT)).isEqualTo(new AccessTokenOwner(ACCOUNT_OF_JOHN, JOHN, HIGHSOFT));
    }

}
