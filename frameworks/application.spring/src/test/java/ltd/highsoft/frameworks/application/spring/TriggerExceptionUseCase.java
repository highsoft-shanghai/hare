package ltd.highsoft.frameworks.application.spring;

import ltd.highsoft.frameworks.application.core.UseCase;
import ltd.highsoft.frameworks.security.core.Authorities;

@UseCase(requiredAuthorities = Authorities.ANONYMOUS)
public class TriggerExceptionUseCase {

    public void execute(RuntimeException e) {
        throw e;
    }

}
