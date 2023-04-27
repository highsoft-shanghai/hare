package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.FixedIdGenerator;
import ltd.highsoft.hare.frameworks.domain.core.Identity;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class AccessTokenFactoryTest {

    public static final GrantedAuthorities GRANTED_AUTHORITIES = GrantedAuthorities.of("f1", "f2");
    private static final Identity USER_ACCOUNT = identity("john@highsoft.ltd", "John@Highsoft");
    private static final Identity USER = identity("john", "John");
    private static final Identity TENANT = identity("highsoft", "Highsoft");
    private @Mock IdentityFetcher identityFetcher;
    private @Mock GrantedAuthoritiesResolver grantedAuthoritiesResolver;
    private AccessTokenFactory factory;

    @BeforeEach
    void setUp() {
        given(identityFetcher.fetchUserAccount(id("john@highsoft.ltd"))).willReturn(USER_ACCOUNT);
        given(identityFetcher.fetchUser(id("john"))).willReturn(identity("", ""));
        given(identityFetcher.fetchTenant(id("highsoft"))).willReturn(TENANT);
        given(grantedAuthoritiesResolver.resolve(id("john@highsoft.ltd"))).willReturn(GRANTED_AUTHORITIES);
        factory = new AccessTokenFactory(new FixedIdGenerator(id("a-fix-id")), grantedAuthoritiesResolver, identityFetcher);
    }

    @Test
    void should_be_able_to_create_access_tokens() {
        AccessToken accessToken = factory.newAccessToken(new CredentialOwner(id("john@highsoft.ltd"), id("john"), id("highsoft")), "web");
        assertThat(accessToken.token().asString()).isEqualTo("a-fix-id");
        assertThat(accessToken.owner()).isEqualTo(new AccessTokenOwner(USER_ACCOUNT, identity("", ""), TENANT));
        assertThat(accessToken.group()).isEqualTo("web");
        assertThat(accessToken.grantedAuthorities()).isEqualTo(GRANTED_AUTHORITIES);
    }

}
