package ltd.highsoft.frameworks.application.spring;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static ltd.highsoft.frameworks.domain.core.Id.id;
import static org.hamcrest.Matchers.is;

public class IdSerializerTest extends IntegrationTest {

    @Test
    void should_be_able_to_serialize_ids() {
        when().get("/test/serialize-id").then().statusCode(200).body("id", is("test-id"));
    }

    @RestController
    static class TestController {

        @GetMapping("/test/serialize-id")
        public Object test() {
            return Map.of("id", id("test-id"));
        }

    }

}
