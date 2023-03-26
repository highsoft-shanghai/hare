package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static ltd.highsoft.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.assertThat;

class IdTest {

    @Test
    void should_be_able_to_compare_to_others() {
        assertThat(id("1")).isEqualTo(id("1"));
        assertThat(id("1")).isNotEqualTo(id("2"));
    }

    @Test
    void should_be_able_to_convert_into_string() {
        assertThat(id("123").asString()).isEqualTo("123");
    }

    @Test
    void should_be_able_to_create_from_strings() {
        assertThat(id("test")).isEqualTo(id("test"));
    }

    @Test
    void should_be_able_to_create_empty_ids() {
        assertThat(id("").isEmpty()).isTrue();
        assertThat(id("")).isEqualTo(Id.EMPTY);
    }

}
