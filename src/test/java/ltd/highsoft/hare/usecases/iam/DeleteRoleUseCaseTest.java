package ltd.highsoft.hare.usecases.iam;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.ApiTest;
import ltd.highsoft.hare.foundations.iam.domain.*;
import ltd.highsoft.hare.frameworks.domain.core.Code;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import ltd.highsoft.hare.frameworks.test.web.Documentation;
import ltd.highsoft.hare.frameworks.test.web.WithGrantedAuthorities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static ltd.highsoft.hare.frameworks.domain.core.Remarks.remarks;
import static ltd.highsoft.hare.frameworks.test.web.Documentation.doc;
import static ltd.highsoft.hare.frameworks.test.web.PathVariables.variables;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;

public class DeleteRoleUseCaseTest extends ApiTest {

    private @Resource Roles roles;
    private @Resource UserAccounts userAccounts;
    private @Resource Users users;
    private @Resource Tenants tenants;

    @BeforeEach
    void setUp() {
        Role role1 = new Role(ScopedId.id("role-1", "highsoft"), name("role-1"), GrantedAuthorities.of(), remarks("Remarks for operators1"), false, Code.code("1"));
        roles.add(role1);
    }

    @Test
    @WithGrantedAuthorities("iam.delete-role")
    void should_delete_role() {
        var response = delete("/roles/{id}", variables(Map.of("id", "role-1")), document());
        response.statusCode(is(204));
    }

    @Test
    @WithGrantedAuthorities("iam.delete-role")
    void should_throw_when_delete_role_with_used_user() {
        UserOwner userOwner = new UserOwner(id("john"), users);
        TenantOwner tenantOwner = new TenantOwner(id("highsoft"), tenants);
        UserAccount userAccount = new UserAccount(id("john@highsoft.ltd"), "John", new UserAccountOwner(userOwner, tenantOwner), new UserAccountRoles(Set.of("role-1"), roles), false);
        userAccounts.add(userAccount);
        var response = delete("/roles/role-1", variables(), document());
        response.statusCode(is(400)).body("message", either(is("仍有用户使用该角色，不能删除")).or(is("Still have user use this role, cannot remove it")));
    }

    @AfterEach
    void tearDown() {
        userAccounts.remove(id("john@highsoft.ltd"));
        roles.remove(ScopedId.id("role-1", "highsoft"));
    }

    @Override
    protected Documentation document() {
        return doc("roles.delete");
    }

}
