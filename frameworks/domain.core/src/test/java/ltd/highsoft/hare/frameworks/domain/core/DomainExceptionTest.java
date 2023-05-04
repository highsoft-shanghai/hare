package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@MockitoSettings
public class DomainExceptionTest {

    private @Mock MessageResolver messageResolver;

    @Test
    void should_not_be_checked_exception() {
        assertThat(new DomainException()).isInstanceOf(RuntimeException.class);
    }

    @Test
    void should_carry_message() {
        assertThat(new DomainException(I18nMessage.message("error-code", "a", "b"))).hasMessage("error-code: [a, b]");
    }

    @Test
    void should_carry_data() {
        assertThat(new DomainException(I18nMessage.message("error-code", "a", "b")).data()).containsExactly("a", "b");
    }

    @Test
    void should_format_as_message() {
        given(messageResolver.resolve("error.a-test-message-code")).willReturn("Error message from resolver");
        var exception = new DomainException(I18nMessage.message("error.a-test-message-code"));
        assertThat(exception.format(messageResolver)).isEqualTo("Error message from resolver");
    }

    @Test
    void should_format_as_message_with_data() {
        given(messageResolver.resolve("error.a-test-message-code", "seg1", "seg2")).willReturn("Error message from resolver");
        var exception = new DomainException(I18nMessage.message("error.a-test-message-code", "seg1", "seg2"));
        assertThat(exception.format(messageResolver)).isEqualTo("Error message from resolver");
    }

    @Test
    void should_carry_cause() {
        assertThat(new DomainException(new RuntimeException())).hasCauseInstanceOf(RuntimeException.class);
        assertThat(new DomainException(new RuntimeException(), I18nMessage.message("error-code"))).hasCauseInstanceOf(RuntimeException.class);
        assertThat(new DomainException(new RuntimeException(), I18nMessage.message("error-code", "a", "b"))).hasCauseInstanceOf(RuntimeException.class);
        assertThat(new DomainException(new RuntimeException(), I18nMessage.message("error-code", "a", "b"))).hasMessage("error-code: [a, b]");
    }

}
