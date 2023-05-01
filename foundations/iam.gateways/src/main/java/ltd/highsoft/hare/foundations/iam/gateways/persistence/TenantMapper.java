package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.Tenant;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TenantMapper {

    private @Resource NamedParameterJdbcTemplate jdbc;


    public void add(Tenant tenant) {
        String sql = "INSERT INTO iam_tenants (id, name) VALUES (:id, :name)";
        jdbc.update(sql, Map.of("id", tenant.id().asString(), "name", tenant.name()));
    }

    public void remove(Id id) {
        String sql = "DELETE FROM iam_tenants WHERE id = :id";
        jdbc.update(sql, Map.of("id", id.asString()));
    }


    public List<Tenant> get() {
        String sql = "SELECT id, name FROM iam_tenants";
        return jdbc.query(sql, (rs, rowNum) -> Tenant.restore(rs.getString("id"), rs.getString("name")));
    }

    public Tenant get(Id id) {
        String sql = "SELECT id, name FROM iam_tenants WHERE id = :id";
        return jdbc.queryForObject(sql, Map.of("id", id.asString()), (rs, rowNum) -> Tenant.restore(rs.getString("id"), rs.getString("name")));
    }
}
