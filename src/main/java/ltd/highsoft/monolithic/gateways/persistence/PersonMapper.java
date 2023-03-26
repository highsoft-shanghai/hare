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
        String sql = "select t.id, t.code, t.name from mgmt_persons t where id=:id";
        return jdbc.queryForStream(sql, Map.of("id", id), asDomain()).findFirst().get();
    }

    public RowMapper<Person> asDomain() {
        return (rs, rowNumber) -> new Person(rs.getString("id"), rs.getString("code"), rs.getString("name"));
    }

}
