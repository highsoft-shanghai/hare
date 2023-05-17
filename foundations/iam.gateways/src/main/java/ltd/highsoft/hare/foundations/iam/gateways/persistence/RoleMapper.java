package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.frameworks.domain.core.*;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

import static ltd.highsoft.hare.frameworks.domain.core.I18nMessage.message;
import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static ltd.highsoft.hare.frameworks.domain.core.Remarks.remarks;

@Component
public class RoleMapper {

    private final NamedParameterJdbcTemplate jdbc;

    public RoleMapper(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Role get(Id id) {
        String sql = "SELECT id, name, authorities, remarks, tenant_id, predefined, code FROM iam_roles WHERE id = :id";
        return doQuery(sql, Map.of("id", id.asString()));
    }

    private Role doQuery(String sql, Map<String, String> params) {
        try {
            return jdbc.queryForObject(sql, params, (rs, rowNum) -> asDomain(rs));
        } catch (EmptyResultDataAccessException e) {
            throw new AggregateNotFoundException(message("error.role-not-found"));
        }
    }

    private static Role asDomain(ResultSet rs) throws SQLException {
        return new Role(
                ScopedId.id(rs.getString("id"), rs.getString("tenant_id")),
                name(rs.getString("name")),
                GrantedAuthorities.fromCommaSeparatedString(rs.getString("authorities")),
                remarks(rs.getString("remarks")),
                rs.getBoolean("predefined"),
                Code.code(rs.getString("code"))
        );
    }

    public Role get(ScopedId id) {
        String sql = "SELECT id, name, authorities, remarks, tenant_id, predefined, code FROM iam_roles WHERE id = :id AND tenant_id = :tenantId";
        return doQuery(sql, Map.of("id", id.id().asString(), "tenantId", id.tenantId().asString()));
    }

    public void save(Role role) {
        if (exists(role.id())) {
            String sql = "UPDATE iam_roles SET name = :name, authorities = :authorities, remarks = :remarks, predefined = :predefined, code = :code WHERE id = :id AND tenant_id = :tenantId";
            jdbc.update(sql, Map.of("id", role.id().id().asString(), "name", role.name().asString(), "authorities", role.grantedAuthorities().toCommaSeparatedString(),
                    "remarks", role.remarks().asString(), "tenantId", role.id().tenantId().asString(), "predefined", role.predefined(), "code", role.code().asString()));
            return;
        }
        String sql = "INSERT INTO iam_roles (id, name, authorities, remarks, tenant_id, predefined, code) VALUES (:id, :name, :authorities, :remarks, :tenantId, :predefined, :code)";
        jdbc.update(sql, Map.of("id", role.id().id().asString(), "name", role.name().asString(), "authorities", role.grantedAuthorities().toCommaSeparatedString(),
                "remarks", role.remarks().asString(), "tenantId", role.id().tenantId().asString(), "predefined", role.predefined(), "code", role.code().asString()));
    }

    public boolean exists(Name name, ScopedId id) {
        if (exists(id)) return true;
        return exists("SELECT COUNT(*) FROM iam_roles WHERE name = :name and tenant_id = :tenantId", Map.of("name", name.asString(), "tenantId", id.tenantId().asString()));
    }

    private boolean exists(ScopedId id) {
        return exists("SELECT COUNT(*) FROM iam_roles WHERE id = :id", Map.of("id", id.id().asString()));
    }

    private boolean exists(String sql, Map<String, String> params) {
        Integer count = jdbc.queryForObject(sql, params, Integer.class);
        return Optional.ofNullable(count).orElse(0) > 0;
    }

    public void remove(ScopedId id) {
        String sql = "DELETE FROM iam_roles WHERE id = :id AND tenant_id = :tenantId";
        jdbc.update(sql, Map.of("id", id.id().asString(), "tenantId", id.tenantId().asString()));
    }
}
