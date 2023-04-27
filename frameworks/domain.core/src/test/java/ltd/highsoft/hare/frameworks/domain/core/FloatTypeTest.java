package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FloatTypeTest {

    @Test
    void should_be_able_to_convert_into_floats_from_floats() {
        assertThat(FloatFieldType.asFloat().from(1.3f, "")).isEqualTo(1.3f);
    }

}
