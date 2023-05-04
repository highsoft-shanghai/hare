package ltd.highsoft.hare.frameworks.test.moco;

import com.github.dreamhead.moco.HttpServer;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@WithExternalService
public class WithExternalServiceTest {

    private static HttpServer serverFromBeforeAll;
    private HttpServer serverFromBeforeEach;

    @BeforeAll
    static void beforeAll(HttpServer server) {
        WithExternalServiceTest.serverFromBeforeAll = server;
    }

    @BeforeEach
    void setUp(HttpServer server) {
        this.serverFromBeforeEach = server;
    }

    @Test
    void should_resolve_http_server_parameters_in_before_all() {
        assertThat(WithExternalServiceTest.serverFromBeforeAll).isNotNull();
    }

    @Test
    void should_resolve_http_server_parameters_in_before_each() {
        assertThat(serverFromBeforeEach).isNotNull();
    }

    @Test
    void should_resolve_http_server_parameters(HttpServer server) {
        assertThat(server).isNotNull();
    }

}
