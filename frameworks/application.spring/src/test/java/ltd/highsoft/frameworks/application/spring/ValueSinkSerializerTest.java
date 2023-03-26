package ltd.highsoft.frameworks.application.spring;

import io.restassured.response.ValidatableResponse;
import ltd.highsoft.frameworks.domain.core.ListValueSink;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.hamcrest.Matchers.is;

public class ValueSinkSerializerTest extends IntegrationTest {

    @Test
    void should_be_able_to_serialize_value_sinks() {
        ValidatableResponse response = when().get("/test/serialize-value-sink").then();
        response.statusCode(200);
        response.body("[0]", is(1));
        response.body("[1]", is(1.2f));
        response.body("[2]", is(true));
        response.body("[3]", is("test"));
        response.body("[4]", is(Map.of("name", "John")));
    }

    @RestController
    static class TestController {

        @GetMapping("/test/serialize-value-sink")
        public Object test() {
            ListValueSink result = new ListValueSink();
            result.add(1);
            result.add(1.2f);
            result.add(true);
            result.add("test");
            result.add(Map.of("name", "John"));
            return result;
        }

    }

}
