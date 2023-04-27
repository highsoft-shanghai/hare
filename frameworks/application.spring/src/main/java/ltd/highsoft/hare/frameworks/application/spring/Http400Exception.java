package ltd.highsoft.hare.frameworks.application.spring;

import ltd.highsoft.hare.frameworks.application.core.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Http400Exception extends ApplicationException {

    public Http400Exception(Throwable throwable) {
        super(throwable);
    }

}
