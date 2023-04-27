package ltd.highsoft.hare.frameworks.application.spring;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class ApplicationErrorAttributes extends DefaultErrorAttributes {

    private final ExceptionFormatter exceptionFormatter;

    public ApplicationErrorAttributes(ExceptionFormatter exceptionFormatter) {
        this.exceptionFormatter = exceptionFormatter;
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest request, ErrorAttributeOptions options) {
        var error = super.getErrorAttributes(request, options);
        error.put("message", exceptionFormatter.format(getError(request)));
        return error;
    }

}
