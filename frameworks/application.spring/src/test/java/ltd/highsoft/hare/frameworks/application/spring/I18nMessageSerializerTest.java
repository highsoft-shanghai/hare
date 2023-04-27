package ltd.highsoft.hare.frameworks.application.spring;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.I18nMessage.message;
import static org.hamcrest.Matchers.is;

public class I18nMessageSerializerTest extends IntegrationTest {

    @Test
    void should_be_able_to_serialize_i18n_messages() {
        when().get("/test/serialize-i18n-message").then().statusCode(200).body("message", is("test-message"));
    }

    @RestController
    static class TestController {

        @GetMapping("/test/serialize-i18n-message")
        public Object test() {
            return Map.of("message", message("test-message"));
        }

    }

}
