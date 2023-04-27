package ltd.highsoft.hare.frameworks.application.spring;

import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.security.core.Authorities;

@UseCase(requiredAuthorities = Authorities.ANONYMOUS)
public class TriggerExceptionUseCase {

    public void execute(RuntimeException e) {
        throw e;
    }

}
