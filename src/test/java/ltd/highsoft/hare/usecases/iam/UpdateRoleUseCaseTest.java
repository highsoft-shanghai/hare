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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static ltd.highsoft.hare.frameworks.domain.core.Remarks.remarks;
import static ltd.highsoft.hare.frameworks.test.web.ConstrainedFields.constrainedFieldWithPath;
import static ltd.highsoft.hare.frameworks.test.web.PathVariables.variables;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

public class UpdateRoleUseCaseTest extends ApiTest {

    private @Resource Roles roles;

    @BeforeEach
    public void setUp() {
        Role role1 = new Role(ScopedId.id("role-1", "highsoft"), name("role-1"), GrantedAuthorities.of(), remarks("Remarks for operators1"), false);
        roles.add(role1);
    }

    @Test
    @WithGrantedAuthorities({"iam.update-role"})
    public void should_update_role() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("authorities", List.of("basic.management", "basic.tag-group-management", "management.create-tag-group", "management.archive-tag-group"));
        payload.put("name", "role-2");
        payload.put("remarks", "remarks");
        var response = put("/roles/role-1", variables(), payload, document());
        response.statusCode(204);
        Role role = roles.get(id("role-1"));
        assertThat(role.grantedAuthorities().asSet()).containsExactlyInAnyOrder("basic.management", "basic.tag-group-management", "management.create-tag-group", "management.archive-tag-group");
        assertThat(role.name().asString()).isEqualTo("role-2");
    }

    @AfterEach
    public void tearDown() {
        roles.remove(ScopedId.id("role-1", "highsoft"));
    }

    @Override
    protected Documentation document() {
        return Documentation.doc("role.put",
                requestFields(
                        constrainedFieldWithPath("name", "must").description("Role name"),
                        constrainedFieldWithPath("authorities", "optional").description("Authorities of role"),
                        constrainedFieldWithPath("remarks", "optional").optional().description("remarks")
                )
        );
    }

}
