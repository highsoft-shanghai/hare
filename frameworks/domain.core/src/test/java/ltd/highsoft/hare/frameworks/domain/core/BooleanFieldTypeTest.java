package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BooleanFieldTypeTest {

    @Test
    void should_convert_into_booleans_from_boolean_values() {
        assertThat(BooleanFieldType.asBoolean().from(true, "")).isEqualTo(true);
    }

}
