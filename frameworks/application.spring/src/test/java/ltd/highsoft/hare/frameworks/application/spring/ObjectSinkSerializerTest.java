package ltd.highsoft.hare.frameworks.application.spring;

import io.restassured.response.ValidatableResponse;
import ltd.highsoft.hare.frameworks.domain.core.MapObjectSink;
import ltd.highsoft.hare.frameworks.domain.core.ObjectSink;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.hamcrest.Matchers.is;

public class ObjectSinkSerializerTest extends IntegrationTest {

    @Test
    void should_be_able_to_serialize_object_sinks() {
        ValidatableResponse response = when().get("/test/serialize-object-sink").then();
        response.statusCode(200);
        response.body("$", is(Map.of("name", "John")));
    }

    @RestController
    static class TestController {

        @GetMapping("/test/serialize-object-sink")
        public Object test() {
            ObjectSink result = new MapObjectSink();
            result.put("name", "John");
            return result;
        }

    }

}
