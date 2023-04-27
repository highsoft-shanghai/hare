package ltd.highsoft.hare.usecases.iam;

import io.restassured.response.ValidatableResponse;
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

import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static ltd.highsoft.hare.frameworks.domain.core.Remarks.remarks;
import static ltd.highsoft.hare.frameworks.test.web.ApiDocUtils.pagedRequestParameters;
import static ltd.highsoft.hare.frameworks.test.web.ApiDocUtils.pagedResponseFields;
import static ltd.highsoft.hare.frameworks.test.web.ConstrainedParameters.parameterWithConstraints;
import static ltd.highsoft.hare.frameworks.test.web.Documentation.doc;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class SearchRolesUseCaseTest extends ApiTest {

    private @Resource Roles roles;

    @BeforeEach
    void setUp() {
        Role role1 = new Role(ScopedId.id("role-1", "highsoft"), name("role-1"), GrantedAuthorities.of(), remarks("Remarks for operators1"), false);
        Role role2 = new Role(ScopedId.id("role-2", "highsoft"), name("role-2"), GrantedAuthorities.of(), remarks("Remarks for operators2"), false);
        roles.add(role1);
        roles.add(role2);
    }

    @Test
    @WithGrantedAuthorities("iam.search-roles")
    void should_be_able_to_search_roles_by_name() {
        ValidatableResponse response = get("/roles?q=2&page=0&size=3", document());
        response.statusCode(200)
            .body("numberOfTotalElements", is(1))
            .body("numberOfElements", is(1))
            .body("content", hasSize(1))
            .body("content[0].id", is("role-2"));
    }

    @AfterEach
    void tearDown() {
        roles.remove(ScopedId.id("role-1", "highsoft"));
        roles.remove(ScopedId.id("role-2", "highsoft"));
    }

    @Override
    protected Documentation document() {
        return doc("role.get",
            pagedRequestParameters(
                parameterWithConstraints("q", "String", "").description("检索关键字：名称、备注")
            ),
            pagedResponseFields(
                fieldWithPath("content[].id").description("角色ID"),
                fieldWithPath("content[].name").description("角色名称"),
                fieldWithPath("content[].remarks").description("角色备注")
            ));
    }

}
