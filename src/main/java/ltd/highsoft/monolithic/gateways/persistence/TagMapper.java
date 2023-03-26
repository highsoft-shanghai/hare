package ltd.highsoft.monolithic.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.monolithic.domain.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TagMapper {

    private @Resource NamedParameterJdbcTemplate jdbc;

    public Tag get(String id) {
        String sql = "select t.id, t.code, t.name from mgmt_tags t where id=:id";
        return jdbc.queryForStream(sql, Map.of("id", id), asDomain()).findFirst().get();
    }

    public RowMapper<Tag> asDomain() {
        return (rs, rowNumber) -> new Tag(rs.getString("id"), rs.getString("code"), rs.getString("name"));
    }

}
