package ltd.highsoft.frameworks.application.spring;

import ltd.highsoft.frameworks.domain.core.*;
import ltd.highsoft.frameworks.security.core.AuthorizationException;
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
