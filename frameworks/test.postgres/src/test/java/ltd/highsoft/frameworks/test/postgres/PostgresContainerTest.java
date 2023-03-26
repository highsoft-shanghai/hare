package ltd.highsoft.frameworks.test.postgres;

import ltd.highsoft.frameworks.test.container.WithTestContainers;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

@WithTestContainers(containers = PostgresContainer.class)
class PostgresContainerTest {

    private Connection connection;

    @BeforeEach
    void setUp() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        var props = new Properties();
        props.setProperty("user", System.getProperty("spring.datasource.username"));
        props.setProperty("password", System.getProperty("spring.datasource.password"));
        connection = DriverManager.getConnection(System.getProperty("spring.datasource.url"), props);
    }

    @Test
    void should_launch_mongodb_test_container() throws SQLException {
        var resultSet = connection.createStatement().executeQuery("select 100");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getInt(1)).isEqualTo(100);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.close();
    }

}
