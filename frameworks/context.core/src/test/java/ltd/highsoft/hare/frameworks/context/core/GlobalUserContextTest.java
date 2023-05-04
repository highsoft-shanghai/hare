package ltd.highsoft.hare.frameworks.context.core;

import ltd.highsoft.hare.frameworks.domain.core.Identity;
import org.junit.jupiter.api.*;

import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;
import static org.assertj.core.api.Assertions.assertThat;

class GlobalUserContextTest {

    public static final Identity JOHN_AT_HIGHSOFT = identity("john@highsoft", "John");
    public static final Identity JOHN = identity("john", "John");
    public static final Identity HIGHSOFT = identity("highsoft", "Highsoft");

    @BeforeEach
    void setUp() {
        GlobalUserContextResetter.reset(new SimpleUserContext(JOHN_AT_HIGHSOFT, JOHN, HIGHSOFT));
    }

    @Test
    void should_hold_current_user_account() {
        assertThat(GlobalUserContext.currentUserAccount()).isEqualTo(JOHN_AT_HIGHSOFT);
        assertThat(GlobalUserContext.currentUserAccountId()).isEqualTo(JOHN_AT_HIGHSOFT.id());
    }

    @Test
    void should_hold_current_user() {
        assertThat(GlobalUserContext.currentUser()).isEqualTo(JOHN);
        assertThat(GlobalUserContext.currentUserId()).isEqualTo(JOHN.id());
    }

    @Test
    void should_hold_current_tenant() {
        assertThat(GlobalUserContext.currentTenant()).isEqualTo(HIGHSOFT);
        assertThat(GlobalUserContext.currentTenantId()).isEqualTo(HIGHSOFT.id());
    }

    @Test
    void should_clear() {
        GlobalUserContextResetter.clear();
        assertThat(GlobalUserContext.userContext()).isEqualTo(UserContext.ANONYMOUS);
    }

    @AfterEach
    void tearDown() {
        GlobalUserContextResetter.clear();
    }

}
