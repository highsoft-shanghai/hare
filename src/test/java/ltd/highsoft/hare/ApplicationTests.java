package ltd.highsoft.hare;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class ApplicationTests extends IntegrationTest {

    private @Resource Persons persons;
    private @Resource JdbcTemplate jdbc;

    @Test
    public void should_be_able_to_load_persons_from_database() {
        jdbc.update("insert into persons(id, code, name, remarks) values(?, ?, ?, ?)", "0001", "0001", "John", "A very good boy");
        Person person = persons.get("0001");
        assertThat(person.id()).isEqualTo("0001");
        assertThat(person.code()).isEqualTo("0001");
        assertThat(person.name()).isEqualTo("John");
        assertThat(person.remarks()).isEqualTo("A very good boy");
    }

    @Test
    public void should_be_able_to_store_persons_into_database() {
        persons.add(new Person("0002", "0002", "Kite", "A very good girl"));
        assertThat(jdbc.queryForMap("select id, code, name, remarks from persons where id=?", "0002")).isEqualTo(Map.of("id", "0002", "code", "0002", "name", "Kite", "remarks", "A very good girl"));
    }

}
