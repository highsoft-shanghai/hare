package ltd.highsoft.frameworks.application.spring;

import ltd.highsoft.frameworks.application.core.UseCase;
import ltd.highsoft.frameworks.domain.core.*;
import ltd.highsoft.frameworks.security.core.*;
import org.springframework.web.bind.annotation.*;

import static ltd.highsoft.frameworks.domain.core.I18nMessage.message;

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
