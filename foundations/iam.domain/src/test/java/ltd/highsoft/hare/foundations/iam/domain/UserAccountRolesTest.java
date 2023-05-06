package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Code;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Set;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static ltd.highsoft.hare.frameworks.domain.core.Remarks.remarks;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class UserAccountRolesTest {

    private @Mock Roles roles;
    private static final Role ROLE_A = new Role(ScopedId.id("role-a", "highsoft"), name("Role A"), GrantedAuthorities.of("f1"), remarks("Role A remarks"), false, Code.code("1"));
    private static final Role ROLE_B = new Role(ScopedId.id("role-b", "highsoft"), name("Role B"), GrantedAuthorities.of("f2"), remarks("Role A remarks"), false, Code.code("1"));

    @Test
    void should_merge_granted_authorities_from_all_related_roles() {
        given(roles.get(id("role-a"))).willReturn(ROLE_A);
        given(roles.get(id("role-b"))).willReturn(ROLE_B);
        UserAccountRoles accountRoles = new UserAccountRoles(Set.of("role-a", "role-b"), roles);
        assertThat(accountRoles.grantedAuthorities()).isEqualTo(GrantedAuthorities.of("f1", "f2", "role-a", "role-b"));
    }

}
