package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MapFieldTypeTest {

    @Test
    void should_convert_into_maps_from_maps() {
        assertThat(MapFieldType.asMap().from(Map.of("a", "b"), "")).isEqualTo(Map.of("a", "b"));
    }

}
