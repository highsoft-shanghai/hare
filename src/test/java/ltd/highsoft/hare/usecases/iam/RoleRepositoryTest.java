package ltd.highsoft.hare.usecases.iam;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.IntegrationTest;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.frameworks.domain.core.AggregateNotFoundException;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoleRepositoryTest extends IntegrationTest {
    private @Resource Roles.RoleRepository roleRepository;

    @Test
    public void should_throw_error_when_role_not_exists_id() {
        Assertions.assertThrowsExactly(AggregateNotFoundException.class, () -> roleRepository.get(Id.id("role-1")), "error.role-not-found");
    }

    @Test
    public void should_throw_error_when_role_not_exists_scoped_id() {
        Assertions.assertThrowsExactly(AggregateNotFoundException.class, () -> roleRepository.get(ScopedId.id("role-1", "highsoft")), "error.role-not-found");
    }
}
