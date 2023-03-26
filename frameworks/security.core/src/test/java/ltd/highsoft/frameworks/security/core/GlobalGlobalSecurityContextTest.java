package ltd.highsoft.frameworks.security.core;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

@WithSecurityContext(grantedAuthorities = {"f1", "f2"})
public class GlobalGlobalSecurityContextTest {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Test
    void should_be_able_to_reset_security_context() {
        assertThat(GlobalSecurityContext.securityContext().grantedAuthorities().asSet()).containsExactlyInAnyOrder("f1", "f2");
    }

    @Test
    void should_be_able_to_reset_to_defaults() {
        GlobalSecurityContextResetter.clear();
        assertThat(GlobalSecurityContext.securityContext()).isEqualTo(SecurityContext.ANONYMOUS);
    }

    @Test
    void should_be_able_to_set_to_anonymous_for_new_threads() throws InterruptedException, ExecutionException {
        Future<SecurityContext> context = executor.submit(GlobalSecurityContext::securityContext);
        assertThat(context.get()).isEqualTo(SecurityContext.ANONYMOUS);
    }

}
