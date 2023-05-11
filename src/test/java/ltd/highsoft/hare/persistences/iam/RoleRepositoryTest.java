package ltd.highsoft.hare.persistences.iam;

import ltd.highsoft.hare.PostgresTest;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.foundations.iam.gateways.persistence.DbRoleRepository;
import ltd.highsoft.hare.foundations.iam.gateways.persistence.RoleMapper;
import ltd.highsoft.hare.frameworks.domain.core.AggregateNotFoundException;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoleRepositoryTest extends PostgresTest {
    private Roles.RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        roleRepository = new DbRoleRepository(new RoleMapper(jdbc()));
    }

    @Test
    public void should_throw_error_when_role_not_exists_id() {
        Assertions.assertThrowsExactly(AggregateNotFoundException.class, () -> roleRepository.get(Id.id("role-1")), "error.role-not-found");
    }

    @Test
    public void should_throw_error_when_role_not_exists_scoped_id() {
        Assertions.assertThrowsExactly(AggregateNotFoundException.class, () -> roleRepository.get(ScopedId.id("role-1", "highsoft")), "error.role-not-found");
    }
}
