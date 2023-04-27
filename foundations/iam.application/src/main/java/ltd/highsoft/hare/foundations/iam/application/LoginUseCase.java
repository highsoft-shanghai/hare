package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.LoginFactory;
import ltd.highsoft.hare.foundations.iam.domain.LoginResult;
import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.domain.core.Payload;
import ltd.highsoft.hare.frameworks.security.core.Authorities;

@UseCase(requiredAuthorities = Authorities.ANONYMOUS)
public class LoginUseCase {

    private final LoginFactory loginFactory;

    public LoginUseCase(LoginFactory loginFactory) {
        this.loginFactory = loginFactory;
    }

    public LoginResult execute(Payload payload) {
        return loginFactory.create().submit(payload);
    }

}
