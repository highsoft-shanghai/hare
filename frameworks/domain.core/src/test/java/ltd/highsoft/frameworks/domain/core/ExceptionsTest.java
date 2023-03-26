package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
public class ExceptionsTest {

    private @Mock TestForExceptions mock;

    @Test
    void should_wrap_exception_for_void_functions() throws IOException {
        RuntimeException runtimeException = new RuntimeException();
        doThrow(runtimeException).when(mock).execute();
        assertThatThrownBy(() -> Exceptions.execute(mock::execute)).isInstanceOf(RuntimeException.class).isSameAs(runtimeException);
    }

    @Test
    void should_wrap_unchecked_exception_for_void_functions() throws IOException {
        doThrow(new IOException()).when(mock).execute();
        assertThatThrownBy(() -> Exceptions.execute(mock::execute)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void should_wrap_exception_for_functions_with_result() throws IOException {
        RuntimeException runtimeException = new RuntimeException();
        when(mock.evaluate()).thenThrow(runtimeException);
        assertThatThrownBy(() -> Exceptions.evaluate(mock::evaluate)).isInstanceOf(RuntimeException.class).isSameAs(runtimeException);
    }

    @Test
    void should_wrap_unchecked_exception_for_functions_with_result() throws IOException {
        when(mock.evaluate()).thenThrow(new IOException());
        assertThatThrownBy(() -> Exceptions.evaluate(mock::evaluate)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void should_be_able_to_execute_method_when_method_is_good() throws IOException {
        when(mock.evaluate()).thenReturn("");
        Exceptions.execute(mock::execute);
        then(mock).should(only()).execute();
    }

    @Test
    void should_be_able_to_evaluate_method_when_method_is_good() {
        assertEquals("test", Exceptions.evaluate(() -> "test"));
    }

    private interface TestForExceptions {

        void execute() throws IOException;

        String evaluate() throws IOException;

    }

}
