package ltd.highsoft.frameworks.application.spring;

import ltd.highsoft.frameworks.application.core.UseCase;
import ltd.highsoft.frameworks.security.core.Authorities;

@UseCase(requiredAuthorities = Authorities.ANONYMOUS)
public class TestUseCase {

    public String execute(String input) {
        return String.format("executed with '%s'", input);
    }

}
