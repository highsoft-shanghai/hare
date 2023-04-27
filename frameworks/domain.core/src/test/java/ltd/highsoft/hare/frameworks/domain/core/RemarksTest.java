package ltd.highsoft.hare.frameworks.domain.core;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RemarksTest {

    @Test
    void should_be_able_to_hold_valid_values() {
        Assertions.assertThat(Remarks.remarks("Pretty good").asString()).isEqualTo("Pretty good");
    }

    @Test
    void should_be_able_to_hold_empty_values() {
        Assertions.assertThat(Remarks.remarks("").asString()).isEqualTo("");
        Assertions.assertThat(Remarks.remarks(null).asString()).isEqualTo("");
    }

    @Test
    void should_be_able_to_compare_to_others() {
        Assertions.assertThat(Remarks.remarks("Pretty good")).isEqualTo(Remarks.remarks("Pretty good"));
        Assertions.assertThat(Remarks.remarks("Pretty good")).isNotEqualTo(Remarks.remarks("Not good"));
    }

    @Test
    void should_reject_too_long_remarks() {
        Throwable throwable = catchThrowable(() -> new Remarks(StringUtils.repeat("x", 2001)));
        assertThat(throwable).isInstanceOf(BadInputException.class);
        assertThat(throwable).hasMessage("error.remarks-length-error: [2000]");
    }

}
