package ltd.highsoft.hare.usecases.iam;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.ApiTest;
import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.test.web.Documentation;
import ltd.highsoft.hare.frameworks.test.web.WithGrantedAuthorities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.ScopedId.id;
import static ltd.highsoft.hare.frameworks.test.web.PathVariables.variables;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class CreateRoleUseCaseTest extends ApiTest {
    private Id createRoleId;
    private @Resource Roles roles;

    @Test
    @WithGrantedAuthorities("iam.create-role")
    public void should_create_role_correctly() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "Manager");
        payload.put("code", "code");
        payload.put("authorities", List.of("basic.management", "basic.tag-group-management", "management.create-tag-group", "management.archive-tag-group"));
        payload.put("remarks", "remarks");
        var response = post("/roles", variables(), payload, document());
        response.statusCode(is(201)).body("id", hasLength(32));
        createRoleId = Id.id(response.extract().body().path("id"));
        Role role = roles.get(createRoleId);
        assertThat(role.grantedAuthorities().asSet()).containsExactlyInAnyOrder("basic.management", "basic.tag-group-management", "management.create-tag-group", "management.archive-tag-group");
        assertThat(role.name().asString()).isEqualTo("Manager");
        assertThat(role.code().asString()).isEqualTo("code");
    }

    @AfterEach
    public void tearDown() {
        roles.remove(id(createRoleId, Id.id("highsoft")));
    }

    @Override
    protected Documentation document() {
        return Documentation.doc("roles.post",
                requestFields(
                        fieldWithPath("code").description("业务编号"),
                        fieldWithPath("name").description("角色名称"),
                        fieldWithPath("authorities").description("权限列表"),
                        fieldWithPath("remarks").description("备注")
                ),
                responseFields(fieldWithPath("id").description("新增角色ID")));
    }

}
