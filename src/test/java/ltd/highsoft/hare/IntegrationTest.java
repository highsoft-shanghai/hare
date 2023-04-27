package ltd.highsoft.hare;

import ltd.highsoft.hare.frameworks.test.container.WithTestContainers;
import ltd.highsoft.hare.frameworks.test.postgres.PostgresContainer;
import ltd.highsoft.hare.frameworks.test.web.RestTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("ci")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithTestContainers(containers = {PostgresContainer.class})
public abstract class IntegrationTest extends RestTest {

}
