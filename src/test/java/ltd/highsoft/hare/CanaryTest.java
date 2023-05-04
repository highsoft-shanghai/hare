package ltd.highsoft.hare;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CanaryTest extends IntegrationTest {

    @Test
    void should_run_tests() {
        assertThat("ok").isEqualTo("ok");
    }

}
