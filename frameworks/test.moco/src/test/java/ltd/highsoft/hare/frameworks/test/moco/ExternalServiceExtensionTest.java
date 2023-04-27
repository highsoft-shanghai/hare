package ltd.highsoft.hare.frameworks.test.moco;

import com.github.dreamhead.moco.HttpServer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ExternalServiceExtension.class)
public class ExternalServiceExtensionTest {

    @Test
    void should_resolve_empty_http_server_parameters_when_no_with_external_service_specified(HttpServer server) {
        assertThat(server).isNull();
    }

    @Test
    void should_not_resolve_other_type_parameters(TestInfo info) {
        assertThat(info).isNotNull();
    }

}
