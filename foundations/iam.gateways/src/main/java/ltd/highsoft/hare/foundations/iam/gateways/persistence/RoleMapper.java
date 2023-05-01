package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.frameworks.domain.core.*;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static ltd.highsoft.hare.frameworks.domain.core.Remarks.remarks;

@Component
public class RoleMapper {

    private @Resource NamedParameterJdbcTemplate jdbc;

    public Role get(Id id) {
        String sql = "SELECT id, name, authorities, remarks, tenant_id, predefined FROM iam_roles WHERE id = :id";
        return jdbc.queryForObject(sql, Map.of("id", id.asString()), (rs, rowNum) -> asDomain(rs));
    }

    private static Role asDomain(ResultSet rs) throws SQLException {
        return new Role(ScopedId.id(rs.getString("id"), rs.getString("tenant_id")), name(rs.getString("name")), GrantedAuthorities.fromCommaSeparatedString(rs.getString("authorities")), remarks(rs.getString("remarks")), rs.getBoolean("predefined"));
    }

    public Role get(ScopedId id) {
        String sql = "SELECT id, name, authorities, remarks, tenant_id, predefined FROM iam_roles WHERE id = :id AND tenant_id = :tenantId";
        return jdbc.queryForObject(sql, Map.of("id", id.id().asString(), "tenantId", id.tenantId().asString()), (rs, rowNum) -> asDomain(rs));
    }

    public void add(Role role) {
        if (exists(role.id())) {
            String sql = "UPDATE iam_roles SET name = :name, authorities = :authorities, remarks = :remarks, predefined = :predefined WHERE id = :id AND tenant_id = :tenantId";
            jdbc.update(sql, Map.of("id", role.id().id().asString(), "name", role.name().asString(), "authorities", role.grantedAuthorities().toCommaSeparatedString(),
                    "remarks", role.remarks().asString(), "tenantId", role.id().tenantId().asString(), "predefined", role.predefined()));
            return;
        }
        String sql = "INSERT INTO iam_roles (id, name, authorities, remarks, tenant_id, predefined) VALUES (:id, :name, :authorities, :remarks, :tenantId, :predefined)";
        jdbc.update(sql, Map.of("id", role.id().id().asString(), "name", role.name().asString(), "authorities", role.grantedAuthorities().toCommaSeparatedString(),
                "remarks", role.remarks().asString(), "tenantId", role.id().tenantId().asString(), "predefined", role.predefined()));
    }

    private boolean exists(ScopedId id) {
        String sql = "SELECT COUNT(*) FROM iam_roles WHERE id = :id and tenant_id = :tenantId";
        return jdbc.queryForObject(sql, Map.of("id", id.id().asString(), "tenantId", id.tenantId().asString()), Integer.class) > 0;
    }

    public Page<Role> search(Id tenantId, String q, Pagination pagination) {
        String sql = "SELECT id, name, authorities, remarks, tenant_id, predefined FROM iam_roles WHERE (name LIKE :q OR remarks LIKE :q) AND tenant_id = :tenantId ORDER BY name ASC LIMIT :limit OFFSET :offset";
        List<Role> query = jdbc.query(sql, Map.of("q", "%" + q + "%", "tenantId", tenantId.asString(), "limit", pagination.limit(), "offset", pagination.offset()), (rs, rowNum) -> asDomain(rs));
        Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM iam_roles WHERE (name LIKE :q OR remarks LIKE :q) AND tenant_id = :tenantId", Map.of("q", "%" + q + "%", "tenantId", tenantId.asString()), Integer.class);
        return GeneralPage.from(query, pagination, count);
    }

    public void remove(ScopedId id) {
        String sql = "DELETE FROM iam_roles WHERE id = :id AND tenant_id = :tenantId";
        jdbc.update(sql, Map.of("id", id.id().asString(), "tenantId", id.tenantId().asString()));
    }
}
