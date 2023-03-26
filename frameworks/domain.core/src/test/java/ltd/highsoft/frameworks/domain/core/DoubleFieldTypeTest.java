package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DoubleFieldTypeTest {

    @Test
    void should_be_able_to_convert_into_doubles_from_doubles() {
        assertThat(DoubleFieldType.asDouble().from(1.3, "")).isEqualTo(1.3);
    }

}
