package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@MockitoSettings
class UserAccountTest {

    private final User user = new User("id", "name", false);
    private final Tenant tenant = Tenant.restore("id", "name");
    private @Mock UserAccountRoles roles;
    private @Mock Users users;
    private @Mock Tenants tenants;

    @BeforeEach
    void setUp() {
        when(users.get(any())).thenReturn(user);
        when(tenants.get(any())).thenReturn(tenant);
    }

    @Test
    void should_be_able_to_get_granted_authorities_from_all_played_roles() {
        given(roles.grantedAuthorities()).willReturn(GrantedAuthorities.of("f1", "f2"));
        Id john = id("john");
        UserOwner userOwner = new UserOwner(john, users);
        TenantOwner tenantOwner = new TenantOwner(id("highsoft"), tenants);
        UserAccount userAccount = new UserAccount(id("john@highsoft.ltd"), "John", new UserAccountOwner(userOwner, tenantOwner), roles, false);
        assertThat(userAccount.grantedAuthorities()).isEqualTo(GrantedAuthorities.of("f1", "f2"));
        assertSame(user, userAccount.owner().user());
        assertSame(tenant, userAccount.owner().tenant());
    }

}
