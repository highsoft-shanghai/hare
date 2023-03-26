package ltd.highsoft.frameworks.test.postgres;

import ltd.highsoft.frameworks.test.container.TestContainer;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainer extends TestContainer<PostgreSQLContainer<?>> {

    @Override
    protected PostgreSQLContainer<?> createContainer() {
        return new PostgreSQLContainer<>("postgres:15");
    }

    @Override
    @SuppressWarnings("resource")
    protected void setupEnvironment() {
        System.setProperty("spring.datasource.url", container().getJdbcUrl());
        System.setProperty("spring.datasource.username", container().getUsername());
        System.setProperty("spring.datasource.password", container().getPassword());
    }

}
