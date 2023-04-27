package ltd.highsoft.hare.frameworks.persistence.jpa;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

public class SpecificationUtilsTest {

    @Test
    void should_return_double_percent_sign_when_empty_string_or_null() {
        AssertionsForClassTypes.assertThat(SpecificationUtils.fullLike("")).isEqualTo("%%");
        AssertionsForClassTypes.assertThat(SpecificationUtils.fullLike(null)).isEqualTo("%%");
    }

    @Test
    void should_return_string_wrapped_by_double_percent_sign_when_not_empty_string() {
        AssertionsForClassTypes.assertThat(SpecificationUtils.fullLike("test")).isEqualTo("%test%");
    }

}
