package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.BadInputException;
import ltd.highsoft.hare.frameworks.domain.core.Name;
import ltd.highsoft.hare.frameworks.domain.core.Remarks;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@MockitoSettings
public class RoleTest {

    private @Mock UserAccounts userAccounts;
    private @Mock UserAccountRoles roles;

    @Test
    void should_throw_error_when_role_is_used() {
        UserAccount userAccount = new UserAccount(id("john@highsoft.ltd"), "John", new UserAccountOwner(new UserOwner(id("john"), null), new TenantOwner(id("highsoft"), null)), roles, false);
        given(userAccounts.searchByRole("role-1")).willReturn(List.of(userAccount));
        Role role = new Role(ScopedId.id("role-1", "highsoft"), Name.name("admin"), GrantedAuthorities.of("f1", "f2"), Remarks.remarks(""), false);
        Throwable throwable = catchThrowable(() -> role.checkIsUse(userAccounts));
        assertThat(throwable).isInstanceOf(BadInputException.class);
        assertThat(throwable).hasMessage("error.role-is-used");
    }

}
