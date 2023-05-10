package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.BadInputException;
import ltd.highsoft.hare.frameworks.domain.core.Code;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static ltd.highsoft.hare.frameworks.domain.core.Remarks.remarks;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings
class RolesTest {

    private @Mock Roles.RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        when(roleRepository.exists(any(), any())).thenReturn(true);
    }

    @Test
    void should_throw_when_add_duplicate_role() {
        assertThrows(BadInputException.class,
                () -> new Roles(roleRepository, null)
                        .save(new Role(
                                ScopedId.id("role-a", "highsoft"),
                                name("Role A"),
                                GrantedAuthorities.of("f1"),
                                remarks("Role A remarks"),
                                false,
                                Code.code("1")))
        );
    }
}
