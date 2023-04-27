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
import static ltd.highsoft.hare.frameworks.test.web.ConstrainedParameters.parameterWithConstraints;
import static ltd.highsoft.hare.frameworks.test.web.RequestParameters.parameters;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;

public class SearchSelectableRolesUseCaseTest extends ApiTest {

    private @Resource Roles roles;

    @BeforeEach
    void setUp() {
        Role role1 = new Role(ScopedId.id("role-1", "highsoft"), name("role-1"), GrantedAuthorities.of(), remarks("Remarks for operators1"), false);
        roles.add(role1);
    }

    @Test
    @WithGrantedAuthorities({})
    public void should_be_able_to_search_selectable_roles() {
        var response = get("/selectable-roles", parameters(Map.of("q", "role-1")), document());
        response.statusCode(is(200));
        response.body("$.size()", is(1));
        response.body("[0].name", is("role-1"));
    }

    @AfterEach
    void tearDown() {
        roles.remove(ScopedId.id("role-1", "highsoft"));
    }

    @Override
    protected Documentation document() {
        return Documentation.doc("selectable-roles.get",
            queryParameters(
                parameterWithConstraints("q", "String", "任意关键字").optional().description("搜索关键字")
            ),
            responseFields(
                fieldWithPath("[].id").description("角色ID"),
                fieldWithPath("[].name").description("角色名称")
            )
        );
    }

}
