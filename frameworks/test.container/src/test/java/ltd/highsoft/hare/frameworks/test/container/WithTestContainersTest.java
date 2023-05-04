package ltd.highsoft.hare.frameworks.test.container;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@WithTestContainers(containers = DummyTestContainer.class)
class WithTestContainersTest {

    @Test
    void should_start_all_test_containers() {
        assertThat(DummyTestContainer.numberOfStarts).isEqualTo(1);
        assertThat(DummyTestContainer.numberOfEnvironmentSetups).isEqualTo(1);
    }

}
