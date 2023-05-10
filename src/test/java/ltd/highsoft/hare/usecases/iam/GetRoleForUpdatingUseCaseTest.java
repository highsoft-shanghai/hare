package ltd.highsoft.hare.usecases.iam;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.ApiTest;
import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.frameworks.domain.core.Code;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import ltd.highsoft.hare.frameworks.test.web.Documentation;
import ltd.highsoft.hare.frameworks.test.web.WithGrantedAuthorities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static ltd.highsoft.hare.frameworks.domain.core.Remarks.remarks;
import static ltd.highsoft.hare.frameworks.test.web.PathVariables.variables;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class GetRoleForUpdatingUseCaseTest extends ApiTest {

    private @Resource Roles roles;

    @BeforeEach
    public void setUp() {
        Role role1 = new Role(ScopedId.id("role-1", "highsoft"), name("role-1"), GrantedAuthorities.of("1"), remarks("Remarks for operators1"), false, Code.code("1234"));
        roles.add(role1);
    }

    @Test
    @WithGrantedAuthorities({"iam.update-role"})
    public void should_get_role() {
        var response = get("/roles/{id}", variables(Map.of("id", "role-1")), document());
        response.statusCode(is(200));
        response.body("id", is("role-1"));
        response.body("code", is("1234"));
        response.body("name", is("role-1"));
        response.body("authorities", is(List.of("1")));
        response.body("remarks", is("Remarks for operators1"));
    }

    @AfterEach
    public void tearDown() {
        roles.remove(ScopedId.id("role-1", "highsoft"));
    }

    @Override
    protected Documentation document() {
        return Documentation.doc("role.get.id",
                responseFields(
                        fieldWithPath("id").description("角色ID"),
                        fieldWithPath("code").description("业务编号"),
                        fieldWithPath("name").description("角色名称"),
                        fieldWithPath("remarks").description("备注"),
                        fieldWithPath("authorities").description("角色权限")
                )
        );
    }
}
