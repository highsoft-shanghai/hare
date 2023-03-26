package ltd.highsoft.frameworks.application.spring;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static ltd.highsoft.frameworks.domain.core.Code.code;
import static org.hamcrest.Matchers.is;

public class CodeSerializerTest extends IntegrationTest {

    @Test
    void should_be_able_to_serialize_codes() {
        when().get("/test/serialize-code").then().statusCode(200).body("code", is("0001"));
    }

    @RestController
    static class TestController {

        @GetMapping("/test/serialize-code")
        public Object test() {
            return Map.of("code", code("0001"));
        }

    }

}
