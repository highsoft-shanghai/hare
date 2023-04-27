package ltd.highsoft.hare.frameworks.application.spring;

import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {

    private @LocalServerPort int port;

    protected RequestSpecification when() {
        return given().port(port).when();
    }

}
