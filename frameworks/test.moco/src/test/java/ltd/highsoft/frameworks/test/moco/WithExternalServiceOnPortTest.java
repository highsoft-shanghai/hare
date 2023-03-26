package ltd.highsoft.frameworks.test.moco;

import com.github.dreamhead.moco.HttpServer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@WithExternalService(port = 33333)
public class WithExternalServiceOnPortTest {

    @Test
    void should_be_able_to_resolve_http_server_parameters_with_specific_ports(HttpServer server) {
        assertThat(server.port()).isEqualTo(33333);
    }

}
