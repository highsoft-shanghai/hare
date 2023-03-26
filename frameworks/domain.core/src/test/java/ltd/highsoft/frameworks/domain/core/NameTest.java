package ltd.highsoft.frameworks.domain.core;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static ltd.highsoft.frameworks.domain.core.Name.name;
import static org.assertj.core.api.Assertions.*;

class NameTest {

    @Test
    void should_be_able_to_hold_valid_values() {
        assertThat(name("John Smith").asString()).isEqualTo("John Smith");
    }

    @Test
    void should_be_able_to_compare_to_others() {
        assertThat(name("John")).isEqualTo(name("John"));
        assertThat(name("John")).isNotEqualTo(name("Kite"));
    }

    @Test
    void should_reject_too_long_names() {
        Throwable throwable = catchThrowable(() -> new Name(StringUtils.repeat("x", 201)));
        assertThat(throwable).isInstanceOf(BadInputException.class);
        assertThat(throwable).hasMessage("error.name-length-error: [1, 200]");
    }

    @Test
    void should_reject_too_short_names() {
        Throwable throwable = catchThrowable(() -> new Name(""));
        assertThat(throwable).isInstanceOf(BadInputException.class);
        assertThat(throwable).hasMessage("error.name-length-error: [1, 200]");
    }

}
