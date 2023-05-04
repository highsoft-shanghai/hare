package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class InstantFieldTypeTest {

    @Test
    void should_convert_into_instants_from_string_values() {
        assertThat(InstantFieldType.asInstant().from("2022-01-01T11:22:33Z", "")).isEqualTo(Instant.parse("2022-01-01T11:22:33Z"));
    }
}
