package ltd.highsoft.hare.usecases.iam;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import ltd.highsoft.hare.frameworks.test.web.Documentation;
import ltd.highsoft.hare.frameworks.test.web.WithGrantedAuthorities;
import ltd.highsoft.hare.ApiTest;
import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static ltd.highsoft.hare.frameworks.domain.core.Remarks.remarks;
import static ltd.highsoft.hare.frameworks.test.web.RequestParameters.parameters;
import static org.hamcrest.Matchers.is;

public class GetSelectableRoleUseCaseTest extends ApiTest {

    private @Resource Roles roles;

    @Override
    protected Documentation document() {
        return null;
    }

    @BeforeEach
    void setUp() {
        Role role1 = new Role(ScopedId.id("role-1", "highsoft"), name("role-1"), GrantedAuthorities.of(), remarks("Remarks for operators1"), false);
        roles.add(role1);
    }

    @Test
    @WithGrantedAuthorities({})
    public void should_be_able_to_get_selectable_role() {
        var response = get("/selectable-roles/role-1", parameters(Map.of("id", "role-1")), document());
        response.statusCode(is(200));
        response.body("$", is(Map.of("id", "role-1", "name", "role-1")));
    }

    @AfterEach
    void tearDown() {
        roles.remove(ScopedId.id("role-1", "highsoft"));
    }

}
