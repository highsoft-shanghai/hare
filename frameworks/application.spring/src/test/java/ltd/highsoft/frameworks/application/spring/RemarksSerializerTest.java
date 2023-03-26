package ltd.highsoft.frameworks.application.spring;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static ltd.highsoft.frameworks.domain.core.Remarks.remarks;
import static org.hamcrest.Matchers.is;

public class RemarksSerializerTest extends IntegrationTest {

    @Test
    void should_be_able_to_serialize_names() {
        when().get("/test/serialize-remarks").then().statusCode(200).body("remarks", is("Pretty good"));
    }

    @RestController
    static class TestController {

        @GetMapping("/test/serialize-remarks")
        public Object test() {
            return Map.of("remarks", remarks("Pretty good"));
        }

    }

}
