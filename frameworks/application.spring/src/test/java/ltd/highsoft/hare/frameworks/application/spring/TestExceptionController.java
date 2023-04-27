package ltd.highsoft.hare.frameworks.application.spring;

import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.domain.core.AggregateNotFoundException;
import ltd.highsoft.hare.frameworks.domain.core.AuthenticationException;
import ltd.highsoft.hare.frameworks.domain.core.BadInputException;
import ltd.highsoft.hare.frameworks.security.core.Authorities;
import ltd.highsoft.hare.frameworks.security.core.AuthorizationException;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import ltd.highsoft.hare.frameworks.security.core.RequiredAuthorities;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ltd.highsoft.hare.frameworks.domain.core.I18nMessage.message;

@RestController
@RequestMapping("/test")
@UseCase(requiredAuthorities = Authorities.ANONYMOUS)
public class TestExceptionController {

    @PostMapping("trigger-aggregate-not-found-exception")
    public void triggerAggregateNotFoundException() {
        throw new AggregateNotFoundException();
    }

    @PostMapping("trigger-bad-input-exception")
    public void triggerIllegalArgumentException() {
        throw new BadInputException(message("error.bad-input"));
    }

    @PostMapping("trigger-authentication-exception")
    public void triggerAuthenticationException() {
        throw new AuthenticationException(message("error.bad-credential"));
    }

    @PostMapping("trigger-authorization-exception")
    public void triggerAuthorizationException() {
        throw new AuthorizationException(message("error.access-denied", RequiredAuthorities.of("f1"), GrantedAuthorities.of("f2")));
    }

}
