package ltd.highsoft.frameworks.test.web;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class YesNoTest {

    @Test
    void should_format_true_as_yes() {
        assertThat(YesNo.of(true).toString()).isEqualTo("Yes");
    }

    @Test
    void should_format_false_as_no() {
        assertThat(YesNo.of(false).toString()).isEqualTo("No");
    }

}
