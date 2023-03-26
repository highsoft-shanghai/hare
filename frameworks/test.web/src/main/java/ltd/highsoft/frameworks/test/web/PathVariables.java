package ltd.highsoft.frameworks.test.web;

import java.util.Map;

public class PathVariables {

    private final Map<String, ?> variables;

    public static PathVariables variables(Map<String, ?> variables) {
        return new PathVariables(variables);
    }

    public static PathVariables variables() {
        return new PathVariables(Map.of());
    }

    public PathVariables(Map<String, ?> variables) {
        this.variables = variables;
    }

    public Map<String, ?> asMap() {
        return variables;
    }

}
