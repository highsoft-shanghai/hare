package ltd.highsoft.frameworks.security.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContextTest {

    @Test
    void should_be_able_to_represent_invalid_context() {
        assertThat(Context.INVALID.userContext().valid()).isFalse();
        assertThat(Context.INVALID.securityContext().valid()).isFalse();
    }

}
