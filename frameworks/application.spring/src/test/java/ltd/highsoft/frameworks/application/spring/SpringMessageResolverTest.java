package ltd.highsoft.frameworks.application.spring;

import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SpringMessageResolverTest {

    private static final String MESSAGE_FROM_MESSAGE_SOURCE = "message-from-message-source";
    private static final String CODE = "code";
    private static final Object[] ARGS = {2, 5};

    @Test
    public void should_be_able_to_resolve_message() {
        var messageSource = mock(MessageSource.class);
        var locale = LocaleContextHolder.getLocale();
        given(messageSource.getMessage(CODE, ARGS, CODE, locale)).willReturn(MESSAGE_FROM_MESSAGE_SOURCE);
        var messageResolver = new SpringMessageResolver(messageSource);
        assertEquals(MESSAGE_FROM_MESSAGE_SOURCE, messageResolver.resolve(CODE, ARGS));
    }

}
