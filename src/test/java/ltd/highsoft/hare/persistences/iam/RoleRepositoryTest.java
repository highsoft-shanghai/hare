package ltd.highsoft.hare.persistences.iam;

import ltd.highsoft.hare.PostgresTest;
import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.foundations.iam.gateways.persistence.DbRoleRepository;
import ltd.highsoft.hare.foundations.iam.gateways.persistence.RoleMapper;
import ltd.highsoft.hare.frameworks.domain.core.AggregateNotFoundException;
import ltd.highsoft.hare.frameworks.domain.core.Code;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static ltd.highsoft.hare.frameworks.domain.core.Remarks.remarks;
import static org.assertj.core.api.Assertions.assertThat;


public class RoleRepositoryTest extends PostgresTest {
    private Roles.RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        roleRepository = new DbRoleRepository(new RoleMapper(jdbc()));
    }

    @Test
    public void should_create_role_succeed() {
        Role role = new Role(ScopedId.id("role-a", "highsoft"), name("Role A"), GrantedAuthorities.of("f1"), remarks("Role A remarks"), false, Code.code("1"));
        roleRepository.save(role);
        Role role1 = roleRepository.get(Id.id("role-a"));
        assertThat(role1.id()).isEqualTo(role.id());
        assertThat(role1.code()).isEqualTo(role.code());
        assertThat(role1.name()).isEqualTo(role.name());
        assertThat(role1.grantedAuthorities()).isEqualTo(role.grantedAuthorities());
        assertThat(role1.remarks()).isEqualTo(role.remarks());
        assertThat(role1.predefined()).isEqualTo(role.predefined());
        roleRepository.remove(ScopedId.id("role-a", "highsoft"));
    }

    @Test
    public void should_get_correct_code_duplication_status() {
        assertThat(roleRepository.codeDuplication(Code.code("1"), ScopedId.id("role-1", "highsoft"))).isFalse();
        roleRepository.save(new Role(ScopedId.id("role-1", "highsoft"), name("Role A"), GrantedAuthorities.of("f1"), remarks("Role A remarks"), false, Code.code("1")));
        roleRepository.save(new Role(ScopedId.id("role-2", "highsoft"), name("Role B"), GrantedAuthorities.of("f2"), remarks("Role B remarks"), false, Code.code("1")));
        assertThat(roleRepository.codeDuplication(Code.code("1"), ScopedId.id("role-1", "highsoft"))).isTrue();
        assertThat(roleRepository.codeDuplication(Code.code("1"), ScopedId.id("role-11", "highsoft"))).isTrue();
        assertThat(roleRepository.codeDuplication(Code.code("1"), ScopedId.id("role-1", "highsoft2"))).isFalse();
        assertThat(roleRepository.codeDuplication(Code.code("2"), ScopedId.id("role-1", "highsoft"))).isFalse();
        roleRepository.remove(ScopedId.id("role-1", "highsoft"));
        roleRepository.remove(ScopedId.id("role-2", "highsoft"));
    }

    @Test
    public void should_get_correct_role_with_scope_id() {
        Role role = new Role(ScopedId.id("role-a", "highsoft"), name("Role A"), GrantedAuthorities.of("f1"), remarks("Role A remarks"), false, Code.code("1"));
        roleRepository.save(role);
        Role role1 = roleRepository.get(ScopedId.id("role-a", "highsoft"));
        assertThat(role1.id()).isEqualTo(role.id());
        assertThat(role1.code()).isEqualTo(role.code());
        assertThat(role1.name()).isEqualTo(role.name());
        assertThat(role1.grantedAuthorities()).isEqualTo(role.grantedAuthorities());
        assertThat(role1.remarks()).isEqualTo(role.remarks());
        assertThat(role1.predefined()).isEqualTo(role.predefined());
        roleRepository.remove(ScopedId.id("role-a", "highsoft"));
    }

    @Test
    public void should_throw_error_when_role_not_exists_id() {
        Assertions.assertThrowsExactly(AggregateNotFoundException.class, () -> roleRepository.get(Id.id("role-1")),
                "error.role-not-found");
    }

    @Test
    public void should_throw_error_when_role_not_exists_scoped_id() {
        Assertions.assertThrowsExactly(AggregateNotFoundException.class, () -> roleRepository.get(ScopedId.id("role-1", "highsoft")),
                "error.role-not-found");
    }

    @Test
    public void should_remove_role_with_scope_id() {
        Role role = new Role(ScopedId.id("role-a", "highsoft"), name("Role A"), GrantedAuthorities.of("f1"), remarks("Role A remarks"), false, Code.code("1"));
        roleRepository.save(role);
        Role role1 = roleRepository.get(ScopedId.id("role-a", "highsoft"));
        assertThat(role1.id()).isEqualTo(role.id());
        roleRepository.remove(ScopedId.id("role-a", "highsoft"));
        Assertions.assertThrowsExactly(AggregateNotFoundException.class, () -> roleRepository.get(ScopedId.id("role-a", "highsoft")),
                "error.role-not-found");
    }
}
