package ltd.highsoft.hare;

import ltd.highsoft.hare.frameworks.test.container.WithTestContainers;
import ltd.highsoft.hare.frameworks.test.postgres.PostgresContainer;
import org.flywaydb.core.Flyway;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@WithTestContainers(containers = PostgresContainer.class)
public abstract class PostgresTest {

    private NamedParameterJdbcTemplate jdbc;

    protected synchronized NamedParameterJdbcTemplate jdbc() {
        if (jdbc == null) {
            setUpMigrate();
            setUpJdbc();
        }
        return jdbc;
    }

    private void setUpMigrate() {
        Flyway.configure().dataSource(url(), username(), password()).locations("classpath:db/migration").load().migrate();
    }

    private void setUpJdbc() {
        this.jdbc = new NamedParameterJdbcTemplate(new DriverManagerDataSource(url(), username(), password()));
    }

    private String password() {
        return System.getProperty("spring.datasource.password");
    }

    private String username() {
        return System.getProperty("spring.datasource.username");
    }

    private String url() {
        return System.getProperty("spring.datasource.url");
    }
}
