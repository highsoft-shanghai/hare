package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class UserAccountGrantedAuthoritiesResolverTest {

    private static final GrantedAuthorities RESOLVED_GRANTED_AUTHORITIES = GrantedAuthorities.of("f1", "f2");
    private @Mock UserAccounts userAccounts;
    private @Mock UserAccount johnAtHighsoft;

    @Test
    void should_be_able_to_resolve_granted_authorities_from_user_accounts() {
        given(userAccounts.get(id("john@highsoft.ltd"))).willReturn(johnAtHighsoft);
        given(johnAtHighsoft.grantedAuthorities()).willReturn(RESOLVED_GRANTED_AUTHORITIES);
        GrantedAuthoritiesResolver resolver = new UserAccountGrantedAuthoritiesResolver(userAccounts);
        assertThat(resolver.resolve(id("john@highsoft.ltd"))).isEqualTo(RESOLVED_GRANTED_AUTHORITIES);
    }

}
