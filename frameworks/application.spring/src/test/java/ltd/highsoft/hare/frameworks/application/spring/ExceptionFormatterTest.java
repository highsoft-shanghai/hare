package ltd.highsoft.hare.frameworks.application.spring;

import ltd.highsoft.hare.frameworks.application.core.ApplicationException;
import ltd.highsoft.hare.frameworks.domain.core.DomainException;
import ltd.highsoft.hare.frameworks.domain.core.MessageResolver;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static ltd.highsoft.hare.frameworks.domain.core.I18nMessage.message;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class ExceptionFormatterTest {

    private @Mock MessageResolver messageResolver;

    @Test
    void should_format_application_exceptions() {
        given(messageResolver.resolve("test", 1, 2)).willReturn("message from resolver");
        var formatter = new ExceptionFormatter(messageResolver);
        assertThat(formatter.format(new ApplicationException(new DomainException(message("test", 1, 2))))).isEqualTo("message from resolver");
    }

    @Test
    void should_format_non_application_exceptions() {
        given(messageResolver.resolve("test")).willReturn("message from resolver");
        var formatter = new ExceptionFormatter(messageResolver);
        assertThat(formatter.format(new RuntimeException("test"))).isEqualTo("message from resolver");
    }

    @Test
    void should_format_null() {
        var formatter = new ExceptionFormatter(messageResolver);
        assertThat(formatter.format(null)).isEqualTo(StringUtils.EMPTY);
    }

}
