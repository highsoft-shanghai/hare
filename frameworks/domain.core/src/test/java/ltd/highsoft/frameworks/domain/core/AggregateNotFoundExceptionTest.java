package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AggregateNotFoundExceptionTest {

    @Test
    void should_be_able_to_carry_rich_error_information() {
        var exception = new AggregateNotFoundException("order", "1");
        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception).hasMessage("error.aggregate-not-found: [order, 1]");
        assertThat(exception.data()).containsExactly("order", "1");
    }

    @Test
    void should_be_able_to_carry_cause() {
        var exception = new AggregateNotFoundException(new RuntimeException(), "order", "1");
        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception).hasMessage("error.aggregate-not-found: [order, 1]");
        assertThat(exception).hasCauseInstanceOf(RuntimeException.class);
        assertThat(exception.data()).containsExactly("order", "1");
    }

}
