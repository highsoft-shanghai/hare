package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LongFieldTypeTest {

    @Test
    void should_convert_into_long_integers_from_long_integers() {
        assertThat(LongFieldType.asLong().from(1L, "")).isEqualTo(1L);
    }

}
