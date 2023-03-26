package ltd.highsoft.frameworks.security.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@WithSecurityContext(grantedAuthorities = {"f1"})
class AuthorizerTest {

    @Test
    void should_be_able_to_authorize_requests_against_security_context() {
        var authorizer = new Authorizer();
        assertDoesNotThrow(() -> authorizer.authorize("f1", "f2"));
    }

}
