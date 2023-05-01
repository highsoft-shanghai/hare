package ltd.highsoft.hare;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.frameworks.test.container.WithTestContainers;
import ltd.highsoft.hare.frameworks.test.postgres.PostgresContainer;
import ltd.highsoft.hare.frameworks.test.web.RestTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("ci")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithTestContainers(containers = {PostgresContainer.class})
public abstract class IntegrationTest extends RestTest {

    private @Resource TestAccessToken accessToken;

    protected void clearAccessTokenFromDatabase() {
        accessToken.teardown();
    }

    @BeforeEach
    void setupAccessToken() {
        accessToken.setup();
    }

    @AfterEach
    void teardownAccessToken() {
        accessToken.teardown();
    }

}
