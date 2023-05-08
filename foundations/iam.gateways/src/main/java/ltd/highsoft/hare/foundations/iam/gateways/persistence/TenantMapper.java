package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.Tenant;
import ltd.highsoft.hare.frameworks.domain.core.Code;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Name;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TenantMapper {

    private @Resource NamedParameterJdbcTemplate jdbc;


    public void add(Tenant tenant) {
        String sql = "INSERT INTO iam_tenants (id, name) VALUES (:id, :name)";
        jdbc.update(sql, Map.of("id", tenant.id().asString(), "name", tenant.name().asString()));
    }

    public void remove(Id id) {
        String sql = "DELETE FROM iam_tenants WHERE id = :id";
        jdbc.update(sql, Map.of("id", id.asString()));
    }


    public Tenant get(Id id) {
        String sql = "SELECT id, name FROM iam_tenants WHERE id = :id";
        return jdbc.queryForObject(sql, Map.of("id", id.asString()), (rs, rowNum) -> {
            String id1 = rs.getString("id");
            String name = rs.getString("name");
            return new Tenant(Id.id(id1), Code.code("code-1"), Name.name(name));
        });
    }
}
