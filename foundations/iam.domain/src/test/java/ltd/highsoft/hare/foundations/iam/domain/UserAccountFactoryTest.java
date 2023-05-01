package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.FixedIdGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Set;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.assertThat;

@MockitoSettings
class UserAccountFactoryTest {

    private @Mock Roles roles;

    @Test
    void should_be_able_to_create_user_accounts() {
        UserAccountFactory factory = new UserAccountFactory(new FixedIdGenerator(id("fixed-id")), roles);
        UserAccount userAccount = factory.newUserAccount("John@Highsoft", new UserAccountOwner(new UserOwner(id("john"), null), new TenantOwner(id("highsoft"), null)), Set.of("role-a", "role-b"));
        assertThat(userAccount.id()).isEqualTo(id("fixed-id"));
        assertThat(userAccount.name()).isEqualTo("John@Highsoft");
        assertThat(userAccount.owner()).isEqualTo(new UserAccountOwner(new UserOwner(id("john"), null), new TenantOwner(id("highsoft"), null)));
        assertThat(userAccount.roles().asSet()).isEqualTo(Set.of("role-a", "role-b"));
    }

}
