package ltd.highsoft.frameworks.security.core;

public class Authorizer {

    public void authorize(String... requiredAuthorities) {
        GlobalSecurityContext.securityContext().authorize(RequiredAuthorities.of(requiredAuthorities));
    }

}
