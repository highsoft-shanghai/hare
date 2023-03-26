package ltd.highsoft.frameworks.application.spring;

import ltd.highsoft.frameworks.application.core.ApplicationException;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

class ExceptionTranslatorTest {

    private ExceptionTranslator translator;

    @BeforeEach
    void setUp() {
        translator = new ExceptionTranslator();
        translator.map(IllegalArgumentException.class, Http400Exception::new);
    }

    @Test
    void should_be_able_to_translate_exceptions_by_registered_mappers() {
        var throwable = translator.translate(new IllegalArgumentException("abc"));
        assertThat(throwable).isInstanceOf(Http400Exception.class);
        assertThat(throwable).hasMessage("abc");
    }

    @Test
    void should_be_able_to_translate_exceptions_to_application_exceptions_for_unregistered_exception_types() {
        var throwable = translator.translate(new IllegalStateException("abc"));
        assertThat(throwable).isInstanceOf(ApplicationException.class);
        assertThat(throwable).hasMessage("abc");
    }

    @Test
    void should_not_translate_application_exceptions() {
        var exception = new Http403Exception(new RuntimeException("123"));
        assertThat(translator.translate(exception)).isEqualTo(exception);
    }

}
