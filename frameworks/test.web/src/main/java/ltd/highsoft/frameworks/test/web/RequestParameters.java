package ltd.highsoft.frameworks.test.web;

import java.util.*;

public class RequestParameters {

    private final Map<String, ?> variables;

    public static RequestParameters parameters(Map<String, ?> variables) {
        return new RequestParameters(variables);
    }

    public static RequestParameters parameters() {
        return new RequestParameters(Collections.emptyMap());
    }

    public RequestParameters(Map<String, ?> variables) {
        this.variables = variables;
    }

    public Map<String, ?> asMap() {
        return variables;
    }

}
