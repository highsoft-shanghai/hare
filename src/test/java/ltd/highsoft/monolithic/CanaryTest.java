package ltd.highsoft.monolithic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CanaryTest extends IntegrationTest {

    @Test
    void should_be_able_to_run_tests() {
        assertThat("ok").isEqualTo("ok");
    }

}
