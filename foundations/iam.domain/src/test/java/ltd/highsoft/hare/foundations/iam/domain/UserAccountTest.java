package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class UserAccountTest {

    private @Mock UserAccountRoles roles;

    @Test
    void should_be_able_to_get_granted_authorities_from_all_played_roles() {
        given(roles.grantedAuthorities()).willReturn(GrantedAuthorities.of("f1", "f2"));
        UserAccount userAccount = new UserAccount(id("john@highsoft.ltd"), "John", new UserAccountOwner(id("john"), id("highsoft")), roles, false);
        assertThat(userAccount.grantedAuthorities()).isEqualTo(GrantedAuthorities.of("f1", "f2"));
    }

}
