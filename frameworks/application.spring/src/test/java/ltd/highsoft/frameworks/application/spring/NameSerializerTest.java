package ltd.highsoft.frameworks.application.spring;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static ltd.highsoft.frameworks.domain.core.Name.name;
import static org.hamcrest.Matchers.is;

public class NameSerializerTest extends IntegrationTest {

    @Test
    void should_be_able_to_serialize_names() {
        when().get("/test/serialize-name").then().statusCode(200).body("name", is("John"));
    }

    @RestController
    static class TestController {

        @GetMapping("/test/serialize-name")
        public Object test() {
            return Map.of("name", name("John"));
        }

    }

}
