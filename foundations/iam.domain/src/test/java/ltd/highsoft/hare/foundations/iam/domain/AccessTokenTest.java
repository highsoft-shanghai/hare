package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.context.core.SimpleUserContext;
import ltd.highsoft.hare.frameworks.domain.core.Identity;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import ltd.highsoft.hare.frameworks.security.core.SimpleSecurityContext;
import ltd.highsoft.hare.frameworks.test.context.WithFixedGlobalIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;
import static org.assertj.core.api.Assertions.assertThat;

@WithFixedGlobalIdGenerator("fixed-id")
class AccessTokenTest {

    public static final Identity KITE_AT_HIGHSOFT = identity("kite@highsoft", "Kite");
    public static final Identity KITE = identity("kite", "Kite");
    public static final Identity HIGHSOFT = identity("highsoft", "Highsoft");
    private AccessTokenOwner owner;
    private GrantedAuthorities authorities;
    private AccessToken accessToken;

    @BeforeEach
    void setUp() {
        owner = new AccessTokenOwner(KITE_AT_HIGHSOFT, KITE, HIGHSOFT);
        authorities = GrantedAuthorities.of("f1", "f2");
        accessToken = new AccessToken(id("token-id"), owner, authorities, "web");
    }

    @Test
    void should_carry_owner_info() {
        assertThat(accessToken.owner()).isEqualTo(owner);
    }

    @Test
    void should_carry_granted_authorities() {
        assertThat(accessToken.grantedAuthorities()).isEqualTo(authorities);
    }

    @Test
    void should_provide_user_context() {
        assertThat(accessToken.userContext()).isEqualTo(new SimpleUserContext(KITE_AT_HIGHSOFT, KITE, HIGHSOFT));
    }

    @Test
    void should_provide_security_context() {
        assertThat(accessToken.securityContext()).isEqualTo(new SimpleSecurityContext(id("token-id"), authorities));
    }

}
