package ltd.highsoft.hare.frameworks.domain.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BadInputExceptionTest {

    @Test
    void should_be_able_to_represent_input_errors() {
        var throwable = new BadInputException(I18nMessage.message("error-code"));
        Assertions.assertThat(throwable).isInstanceOf(DomainException.class);
        Assertions.assertThat(throwable).hasMessage("error-code");
    }

}
