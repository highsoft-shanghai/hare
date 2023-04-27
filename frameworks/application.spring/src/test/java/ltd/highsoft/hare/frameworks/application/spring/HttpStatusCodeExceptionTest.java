package ltd.highsoft.hare.frameworks.application.spring;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class HttpStatusCodeExceptionTest extends IntegrationTest {

    @Test
    void should_translate_aggregate_not_found_exception_to_http_404() {
        when().post("/test/trigger-aggregate-not-found-exception").then().statusCode(404).body("message", is("error.aggregate-not-found"));
    }

    @Test
    void should_translate_bad_input_exception_to_http_400() {
        when().post("/test/trigger-bad-input-exception").then().statusCode(400).body("message", is("error.bad-input"));
    }

    @Test
    void should_translate_authentication_exception_to_http_401() {
        when().post("/test/trigger-authentication-exception").then().statusCode(401).body("message", is("error.bad-credential"));
    }

    @Test
    void should_translate_authorization_exception_to_http_403() {
        when().post("/test/trigger-authorization-exception").then().statusCode(403).body("message", is("error.access-denied"));
    }

}
