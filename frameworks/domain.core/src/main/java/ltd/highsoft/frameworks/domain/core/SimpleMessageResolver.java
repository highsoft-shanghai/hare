package ltd.highsoft.frameworks.domain.core;

import java.util.Arrays;

public class SimpleMessageResolver implements MessageResolver {

    @Override
    public String resolve(String code, Object... args) {
        return code + dataAsString(args);
    }

    private String dataAsString(Object[] args) {
        if (args.length < 1) return "";
        return ": " + Arrays.toString(args);
    }

}
