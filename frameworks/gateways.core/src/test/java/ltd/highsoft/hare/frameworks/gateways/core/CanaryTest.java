package ltd.highsoft.hare.frameworks.gateways.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CanaryTest {

    @Test
    void should_run_tests() {
        assertThat("1").hasSize(1);
    }
}
