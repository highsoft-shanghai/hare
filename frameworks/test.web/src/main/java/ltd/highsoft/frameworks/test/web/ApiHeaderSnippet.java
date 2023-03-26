package ltd.highsoft.frameworks.test.web;

import org.springframework.restdocs.operation.Operation;
import org.springframework.restdocs.snippet.TemplatedSnippet;

import java.util.*;

public class ApiHeaderSnippet extends TemplatedSnippet {

    private final YesNo authorizationRequired;

    protected ApiHeaderSnippet(boolean authorizationRequired) {
        super("api-header", null);
        this.authorizationRequired = YesNo.of(authorizationRequired);
    }

    @Override
    protected Map<String, Object> createModel(Operation operation) {
        Map<String, Object> model = new HashMap<>();
        model.put("urlTemplate", getUrlTemplate(operation));
        model.put("httpMethod", operation.getRequest().getMethod());
        model.put("authorizationRequired", authorizationRequired.toString());
        return model;
    }

    private Object getUrlTemplate(Operation operation) {
        String key = "org.springframework.restdocs.urlTemplate";
        return operation.getAttributes().getOrDefault(key, operation.getRequest().getUri().getPath());
    }

}
