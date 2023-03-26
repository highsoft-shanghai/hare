package ltd.highsoft.frameworks.application.core;

import ltd.highsoft.frameworks.domain.core.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class ApplicationExceptionTest {

    private @Mock MessageResolver messageResolver;
    private @Mock DomainException cause;

    @Test
    void should_be_instance_of_unchecked_exception() {
        var exception = new ApplicationException("error-code");
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception).hasMessage("error-code");
    }

    @Test
    void should_be_able_to_format_as_message() {
        given(messageResolver.resolve("error-code")).willReturn("Error message");
        var exception = new ApplicationException("error-code");
        assertThat(exception.format(messageResolver)).isEqualTo("Error message");
    }

    @Test
    void should_delegate_formatting_to_caused_domain_exceptions() {
        given(cause.format(messageResolver)).willReturn("Error message from cause");
        var exception = new ApplicationException(cause);
        assertThat(exception.format(messageResolver)).isEqualTo("Error message from cause");
    }

    @Test
    void should_delegate_message_retrieving_to_caused_exceptions() {
        given(cause.getMessage()).willReturn("Error message from cause");
        var exception = new ApplicationException(cause);
        assertThat(exception).hasMessage("Error message from cause");
    }

}
