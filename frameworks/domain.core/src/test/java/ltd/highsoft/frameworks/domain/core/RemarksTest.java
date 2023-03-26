package ltd.highsoft.frameworks.domain.core;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static ltd.highsoft.frameworks.domain.core.Remarks.remarks;
import static org.assertj.core.api.Assertions.*;

class RemarksTest {

    @Test
    void should_be_able_to_hold_valid_values() {
        assertThat(remarks("Pretty good").asString()).isEqualTo("Pretty good");
    }

    @Test
    void should_be_able_to_hold_empty_values() {
        assertThat(remarks("").asString()).isEqualTo("");
        assertThat(remarks(null).asString()).isEqualTo("");
    }

    @Test
    void should_be_able_to_compare_to_others() {
        assertThat(remarks("Pretty good")).isEqualTo(remarks("Pretty good"));
        assertThat(remarks("Pretty good")).isNotEqualTo(remarks("Not good"));
    }

    @Test
    void should_reject_too_long_remarks() {
        Throwable throwable = catchThrowable(() -> new Remarks(StringUtils.repeat("x", 2001)));
        assertThat(throwable).isInstanceOf(BadInputException.class);
        assertThat(throwable).hasMessage("error.remarks-length-error: [2000]");
    }

}
