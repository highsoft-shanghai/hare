package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IntFieldTypeTest {

    @Test
    void should_be_able_to_convert_into_integers_from_integers() {
        assertThat(IntFieldType.asInt().from(1, "")).isEqualTo(1);
    }

}
