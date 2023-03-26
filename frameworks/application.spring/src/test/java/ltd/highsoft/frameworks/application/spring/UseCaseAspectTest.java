package ltd.highsoft.frameworks.application.spring;

import jakarta.annotation.Resource;
import ltd.highsoft.frameworks.application.core.ApplicationException;
import ltd.highsoft.frameworks.domain.core.AggregateNotFoundException;
import ltd.highsoft.frameworks.test.context.WithSecurityContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@WithSecurityContext(grantedAuthorities = "f1")
public class UseCaseAspectTest extends IntegrationTest {

    private @Resource TriggerExceptionUseCase triggerExceptionUseCase;
    private @Resource TestUseCase testUseCase;

    @Test
    void should_be_able_to_translate_exceptions() {
        var throwable = catchThrowable(() -> triggerExceptionUseCase.execute(new RuntimeException("error-code")));
        assertThat(throwable).isInstanceOf(ApplicationException.class);
    }

    @Test
    void should_be_able_to_translate_aggregate_not_found_exceptions() {
        var throwable = catchThrowable(() -> triggerExceptionUseCase.execute(new AggregateNotFoundException()));
        assertThat(throwable).isInstanceOf(ApplicationException.class);
        assertThat(throwable).hasMessage(AggregateNotFoundException.MESSAGE_CODE);
    }

    @Test
    void should_delegate_execution_to_use_cases() {
        assertThat(testUseCase.execute("test")).isEqualTo("executed with 'test'");
    }

}
