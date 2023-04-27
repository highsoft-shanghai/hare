package ltd.highsoft.hare.usecases.iam;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import ltd.highsoft.hare.frameworks.test.web.Documentation;
import ltd.highsoft.hare.frameworks.test.web.WithGrantedAuthorities;
import ltd.highsoft.hare.ApiTest;
import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static ltd.highsoft.hare.frameworks.domain.core.Remarks.remarks;
import static ltd.highsoft.hare.frameworks.test.web.Documentation.doc;
import static ltd.highsoft.hare.frameworks.test.web.PathVariables.variables;
import static org.hamcrest.Matchers.is;

public class DeleteRoleUseCaseTest extends ApiTest {

    private @Resource Roles roles;

    @BeforeEach
    void setUp() {
        Role role1 = new Role(ScopedId.id("role-1", "highsoft"), name("role-1"), GrantedAuthorities.of(), remarks("Remarks for operators1"), false);
        roles.add(role1);
    }

    @Test
    @WithGrantedAuthorities("iam.delete-role")
    void should_be_able_to_delete_role() {
        var response = delete("/roles/role-1", variables(), document());
        response.statusCode(is(204));
    }

    @Override
    protected Documentation document() {
        return doc("roles.delete");
    }

}
