package ltd.highsoft.hare.frameworks.application.spring;

import ltd.highsoft.hare.frameworks.application.core.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Http401Exception extends ApplicationException {

    public Http401Exception(Throwable throwable) {
        super(throwable);
    }

}
