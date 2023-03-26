package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static ltd.highsoft.frameworks.domain.core.I18nMessage.message;
import static org.assertj.core.api.Assertions.assertThat;

class ValidationExceptionTest {

    @Test
    void should_be_able_to_represent_validation_errors() {
        var throwable = new ValidationException(message("error-code"));
        assertThat(throwable).isInstanceOf(DomainException.class);
        assertThat(throwable).hasMessage("error-code");
    }

}
