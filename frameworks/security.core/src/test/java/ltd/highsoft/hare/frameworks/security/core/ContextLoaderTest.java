package ltd.highsoft.hare.frameworks.security.core;

import ltd.highsoft.hare.frameworks.context.core.GlobalUserContext;
import ltd.highsoft.hare.frameworks.context.core.SimpleUserContext;
import ltd.highsoft.hare.frameworks.context.core.UserContext;
import ltd.highsoft.hare.frameworks.domain.core.Identity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class ContextLoaderTest {

    private static final Identity USER_ACCOUNT_OF_TESTER = identity("tester@highsoft", "Tester");
    private static final Identity USER_OF_TESTER = identity("tester", "Tester");
    private static final Identity TENANT_OF_HIGHSOFT = identity("highsoft", "Highsoft");
    private static final UserContext USER_CONTEXT = new SimpleUserContext(USER_ACCOUNT_OF_TESTER, USER_OF_TESTER, TENANT_OF_HIGHSOFT);
    private static final SecurityContext SECURITY_CONTEXT = new SimpleSecurityContext(id("token-id"), GrantedAuthorities.of("f1", "f2"));
    private static final Context CONTEXT = new SimpleContext(USER_CONTEXT, SECURITY_CONTEXT);
    private @Mock ContextProvider contextProvider;
    private ContextLoader loader;

    @BeforeEach
    void setUp() {
        loader = new ContextLoader(contextProvider);
    }

    @Test
    void should_load_context_from_access_tokens() {
        given(contextProvider.get(id("token-id"))).willReturn(Optional.of(CONTEXT));
        loader.load("token-id");
        assertThat(GlobalUserContext.userContext()).isEqualTo(USER_CONTEXT);
        assertThat(GlobalSecurityContext.securityContext()).isEqualTo(SECURITY_CONTEXT);
    }

    @Test
    void should_load_anonymous_context_when_no_token_id_provided() {
        loader.load("");
        assertThat(GlobalUserContext.userContext()).isEqualTo(UserContext.ANONYMOUS);
        assertThat(GlobalSecurityContext.securityContext()).isEqualTo(SecurityContext.ANONYMOUS);
    }

    @Test
    void should_load_invalid_context_when_token_id_is_invalid() {
        loader.load("a-invalid-token-id");
        assertThat(GlobalUserContext.userContext()).isEqualTo(UserContext.INVALID);
        assertThat(GlobalSecurityContext.securityContext()).isEqualTo(SecurityContext.INVALID);
    }

    @AfterEach
    void tearDown() {
        loader.clear();
    }

}
