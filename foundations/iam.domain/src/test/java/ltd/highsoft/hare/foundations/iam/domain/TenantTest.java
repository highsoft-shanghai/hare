package ltd.highsoft.hare.foundations.iam.domain;

import org.junit.jupiter.api.Test;

import static ltd.highsoft.hare.frameworks.domain.core.Code.code;
import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static org.assertj.core.api.Assertions.assertThat;

class TenantTest {

    // TODO: should move to mapper
    @Test
    public void should_carry_tenant_info() {
        Tenant tenant = new Tenant(id("1"), code("code-1"), name("name"));
        assertThat(tenant.id()).isEqualTo(id("1"));
        assertThat(tenant.name()).isEqualTo(name("name"));
        assertThat(tenant.code()).isEqualTo(code("code-1"));
    }
}