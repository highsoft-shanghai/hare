package ltd.highsoft.frameworks.test.container;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@WithTestContainers(containers = DummyTestContainer.class)
class TestContainersInitializerExtensionTest {

    @Test
    void should_be_able_to_start_all_test_containers() {
        assertThat(DummyTestContainer.numberOfStarts).isEqualTo(1);
        assertThat(DummyTestContainer.numberOfEnvironmentSetups).isEqualTo(1);
    }

}
