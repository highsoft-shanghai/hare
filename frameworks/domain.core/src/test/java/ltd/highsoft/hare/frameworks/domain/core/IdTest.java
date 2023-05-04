package ltd.highsoft.hare.frameworks.domain.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class IdTest {

    @Test
    void should_compare_to_others() {
        Assertions.assertThat(Id.id("11")).isEqualTo(Id.id("1" + "1"));
        Assertions.assertThat(Id.id("1")).isNotEqualTo(Id.id("2"));
    }

    @Test
    void should_convert_into_string() {
        Assertions.assertThat(Id.id("123").asString()).isEqualTo("123");
    }

    @Test
    void should_create_from_strings() {
        Assertions.assertThat(Id.id("test1")).isEqualTo(Id.id("test" + "1"));
    }

    @Test
    void should_create_empty_ids() {
        Assertions.assertThat(Id.id("").isEmpty()).isTrue();
        Assertions.assertThat(Id.id("")).isEqualTo(Id.EMPTY);
    }

}
