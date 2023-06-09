package ltd.highsoft.hare.frameworks.security.core;

public class Authorizer {

    public void authorize(String... requiredAuthorities) {
        GlobalSecurityContext.securityContext().authorize(RequiredAuthorities.of(requiredAuthorities));
    }

}
