package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayFieldTypeTest {

    @Test
    void should_convert_into_lists_from_array_lists() {
        assertThat(StringFieldType.asString().array().from(List.of("1", "2", "3"), "")).containsExactly("1", "2", "3");
    }

    @Test
    void should_convert_into_lists_from_null_values() {
        assertThat(StringFieldType.asString().array().nullToEmpty().from(null, "")).isEqualTo(List.of());
    }

}
