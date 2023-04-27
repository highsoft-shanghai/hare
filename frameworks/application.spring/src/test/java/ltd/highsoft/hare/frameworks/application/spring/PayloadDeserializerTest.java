package ltd.highsoft.hare.frameworks.application.spring;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ltd.highsoft.hare.frameworks.domain.core.Payload;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.StringFieldType.asString;
import static org.hamcrest.Matchers.is;

public class PayloadDeserializerTest extends IntegrationTest {

    @Test
    void should_be_able_to_serialize_ids() {
        ValidatableResponse response = when().body(Map.of("id", "0001", "name", "John")).contentType(ContentType.JSON).post("/test/payload").then();
        response.statusCode(200).body("$", is(Map.of("id", "0001", "name", "John")));
    }

    @RestController
    static class TestController {

        @PostMapping("/test/payload")
        public Object test(@RequestBody Payload payload) {
            return Map.of("id", payload.get("id", asString()), "name", payload.get("name", asString()));
        }

    }

}
