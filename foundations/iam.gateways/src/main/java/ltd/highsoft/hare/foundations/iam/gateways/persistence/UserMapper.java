package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.User;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserMapper {

    private @Resource NamedParameterJdbcTemplate jdbc;


    public void add(User user) {
        String sql = "INSERT INTO iam_users (id, name, predefined) VALUES (:id, :name, :predefined)";
        jdbc.update(sql, Map.of("id", user.id().asString(), "name", user.name(), "predefined", user.predefined()));
    }


    public User get(Id id) {
        String sql = "SELECT id, name, predefined FROM iam_users WHERE id = :id";
        return jdbc.queryForObject(sql, Map.of("id", id.asString()), (rs, rowNum) -> new User(rs.getString("id"), rs.getString("name"), rs.getBoolean("predefined")));
    }


    public void remove(Id id) {
        String sql = "DELETE FROM iam_users WHERE id = :id";
        jdbc.update(sql, Map.of("id", id.asString()));
    }
}
