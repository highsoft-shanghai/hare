package ltd.highsoft.frameworks.application.spring;

import javax.annotation.Nullable;

public class BearerToken {

    public static final String BEARER = "Bearer ";
    private final String value;

    public BearerToken(@Nullable String token) {
        this.value = extractToken(token);
    }

    @Nullable
    private String extractToken(@Nullable String token) {
        if (token == null || !token.startsWith(BEARER)) return null;
        if (token.length() == BEARER.length()) return null;
        return token.substring(BEARER.length());
    }

    @Nullable
    public String value() {
        return value;
    }

}
