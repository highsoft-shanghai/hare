package ltd.highsoft.hare.frameworks.application.spring;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CanaryTest {

    @Test
    void should_run_tests() {
        assertThat("ok").isEqualTo("ok");
    }

}
