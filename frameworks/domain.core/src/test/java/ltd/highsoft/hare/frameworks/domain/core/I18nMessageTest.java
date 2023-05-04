package ltd.highsoft.hare.frameworks.domain.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@MockitoSettings
public class I18nMessageTest {

    private @Mock MessageResolver messageResolver;

    @Test
    void should_format_as_plan_text() {
        var message = I18nMessage.message("message-code");
        given(messageResolver.resolve("message-code")).willReturn("message-from-message-resolver");
        assertThat(message.code()).isEqualTo("message-code");
        assertThat(message.format(messageResolver)).isEqualTo("message-from-message-resolver");
    }

    @Test
    void should_format_as_plan_text_with_data() {
        var message = I18nMessage.message("message-code", "a", "b");
        given(messageResolver.resolve("message-code", "a", "b")).willReturn("message-with-data-from-message-resolver");
        assertThat(message.format(messageResolver)).isEqualTo("message-with-data-from-message-resolver");
    }

    @Test
    void should_provide_data() {
        Assertions.assertThat(I18nMessage.message("message-code", "a", "b").data()).isEqualTo(List.of("a", "b"));
    }

    @Test
    void in_empty_should_provide_blank_message_when_format() {
        assertThat(I18nMessage.EMPTY.format(messageResolver)).isEmpty();
    }

}
