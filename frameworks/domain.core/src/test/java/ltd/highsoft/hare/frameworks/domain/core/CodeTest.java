package ltd.highsoft.hare.frameworks.domain.core;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CodeTest {

    @Test
    void should_be_able_to_hold_valid_values() {
        Assertions.assertThat(Code.code("01").asString()).isEqualTo("01");
    }

    @Test
    void should_be_able_to_compare_to_others() {
        Assertions.assertThat(Code.code("01")).isEqualTo(Code.code("01"));
        Assertions.assertThat(Code.code("01")).isNotEqualTo(Code.code("02"));
    }

    @Test
    void should_reject_too_long_codes() {
        Throwable throwable = catchThrowable(() -> new Code(StringUtils.repeat("1", 101)));
        assertThat(throwable).isInstanceOf(BadInputException.class);
        assertThat(throwable).hasMessage("error.code-length-error: [1, 100]");
    }

    @Test
    void should_reject_too_short_codes() {
        Throwable throwable = catchThrowable(() -> new Code(""));
        assertThat(throwable).isInstanceOf(BadInputException.class);
        assertThat(throwable).hasMessage("error.code-length-error: [1, 100]");
    }

}
