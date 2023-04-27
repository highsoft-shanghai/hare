package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DuplicateAggregateDetectedExceptionTest {

    @Test
    void should_be_able_to_carry_rich_error_information() {
        var exception = new DuplicateAggregateDetectedException("order", "1");
        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception).hasMessage("error.duplicate-aggregate-detected: [order, 1]");
        assertThat(exception.data()).containsExactly("order", "1");
    }

}
