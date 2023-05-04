package ltd.highsoft.hare.frameworks.test.context;

import ltd.highsoft.hare.frameworks.security.core.GlobalSecurityContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@WithSecurityContext(grantedAuthorities = {"f1", "f2"})
public class GlobalSecurityContextExtensionTest {

    @Test
    void should_reset_security_context() {
        assertThat(GlobalSecurityContext.securityContext().grantedAuthorities().asSet()).containsExactlyInAnyOrder("f1", "f2");
    }

}
