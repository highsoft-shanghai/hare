package ltd.highsoft.frameworks.test.context;

import ltd.highsoft.frameworks.security.core.GlobalSecurityContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@WithSecurityContext(grantedAuthorities = {"f1", "f2"})
public class GlobalSecurityContextExtensionTest {

    @Test
    void should_be_able_to_reset_security_context() {
        assertThat(GlobalSecurityContext.securityContext().grantedAuthorities().asSet()).containsExactlyInAnyOrder("f1", "f2");
    }

}
