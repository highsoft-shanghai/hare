package ltd.highsoft.hare.frameworks.test.web;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.Map;

import static ltd.highsoft.hare.frameworks.test.web.PathVariables.variables;
import static ltd.highsoft.hare.frameworks.test.web.RequestParameters.parameters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

public class RestTestTest extends IntegrationTest {

    @Test
    void should_be_able_to_call_simple_get() {
        var response = get("/web-test/simple-get");
        response.statusCode(is(200)).body("name", is("John"));
    }

    @Test
    void should_be_able_to_call_get_with_parameters() {
        var response = get("/web-test/get-with-parameters", parameters(Map.of("q", "test")));
        response.statusCode(is(200)).body("q", is("test"));
    }

    @Test
    void should_be_able_to_call_post_with_body() {
        var response = post("/web-test/post", Map.of("name", "John"));
        response.statusCode(is(200)).body("name", is("John"));
    }

    @Test
    void should_be_able_to_call_post_with_path_variables() {
        var response = post("/web-test/post/{id}", variables(Map.of("id", "1")), Map.of("name", "John"));
        response.statusCode(is(200));
        response.body("id", is("1"));
        response.body("name", is("John"));
    }

    @Test
    void should_be_able_to_post_files() throws FileNotFoundException {
        var response = post("/web-test/post-with-file", variables(Map.of()), ResourceUtils.getFile("classpath:test/sample.txt"), null);
        response.statusCode(is(200));
        response.body(is("sample from file\n"));
    }

    @Test
    void should_be_able_to_call_put_with_body() {
        var response = put("/web-test/put", Map.of("name", "John"));
        response.statusCode(is(200)).body("name", is("John"));
    }

    @Test
    void should_be_able_to_call_delete() {
        var response = delete("/web-test/delete/{id}", variables(Map.of("id", "1")));
        response.statusCode(is(200)).body("id", is("1"));
    }

    @Test
    void should_be_able_to_provide_collect_port_number() {
        assertThat(port()).isPositive();
    }

}
