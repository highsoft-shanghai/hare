package ltd.highsoft.monolithic.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.monolithic.domain.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PersonMapper {

    private @Resource NamedParameterJdbcTemplate jdbc;

    public Person get(String id) {
        String sql = "select p.id, p.code, p.name, p.remarks from persons p where p.id=:id";
        return jdbc.queryForStream(sql, Map.of("id", id), asDomain()).findFirst().get();
    }

    public void add(Person person) {
        String sql = "insert into persons(id, code, name, remarks) values(:id, :code, :name, :remarks)";
        jdbc.update(sql, Map.of("id", person.id(), "code", person.code(), "name", person.name(), "remarks", person.remarks()));
    }

    private RowMapper<Person> asDomain() {
        return (rs, rowNumber) -> new Person(rs.getString("id"), rs.getString("code"), rs.getString("name"), rs.getString("remarks"));
    }

}
