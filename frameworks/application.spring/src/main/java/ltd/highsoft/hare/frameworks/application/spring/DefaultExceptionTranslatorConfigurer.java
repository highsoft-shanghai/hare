package ltd.highsoft.hare.frameworks.application.spring;

import ltd.highsoft.hare.frameworks.domain.core.AggregateNotFoundException;
import ltd.highsoft.hare.frameworks.domain.core.AuthenticationException;
import ltd.highsoft.hare.frameworks.domain.core.BadAccessTokenException;
import ltd.highsoft.hare.frameworks.domain.core.BadInputException;
import ltd.highsoft.hare.frameworks.security.core.AuthorizationException;
import org.springframework.stereotype.Component;

@Component
public class DefaultExceptionTranslatorConfigurer implements ExceptionTranslatorConfigurer {

    @Override
    public void config(ExceptionTranslator translator) {
        translator.map(AggregateNotFoundException.class, Http404Exception::new);
        translator.map(AuthenticationException.class, Http401Exception::new);
        translator.map(BadAccessTokenException.class, Http401Exception::new);
        translator.map(AuthorizationException.class, Http403Exception::new);
        translator.map(BadInputException.class, Http400Exception::new);
    }

}
