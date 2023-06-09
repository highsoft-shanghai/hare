package ltd.highsoft.hare.frameworks.security.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContextTest {

    @Test
    void should_represent_invalid_context() {
        assertThat(Context.INVALID.userContext().valid()).isFalse();
        assertThat(Context.INVALID.securityContext().valid()).isFalse();
    }

}
