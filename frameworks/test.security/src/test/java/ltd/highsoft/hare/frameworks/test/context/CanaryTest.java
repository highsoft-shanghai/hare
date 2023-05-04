package ltd.highsoft.hare.frameworks.test.context;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CanaryTest {

    @Test
    void should_run_tests() {
        assertThat("ok").isEqualTo("ok");
    }

}
