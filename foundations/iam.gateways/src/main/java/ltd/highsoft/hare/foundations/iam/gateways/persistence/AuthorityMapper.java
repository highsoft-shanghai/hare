package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.Authority;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;

@Component
public class AuthorityMapper {

    private @Resource NamedParameterJdbcTemplate jdbc;

    public void add(Authority authority) {
        String sql = "INSERT INTO iam_authorities (id, name, parent_id, is_leaf, remarks) VALUES (:id, :name, :parentId, :isLeaf, :remarks)";
        jdbc.update(sql, Map.of("id", authority.id().asString(), "name", authority.name(), "parentId", authority.parentId().asString(), "isLeaf", authority.isLeaf(), "remarks", authority.remarks()));
    }

    public List<Authority> all() {
        String sql = "SELECT id, name, parent_id, is_leaf, remarks FROM iam_authorities";
        return jdbc.query(sql, (rs, rowNum) -> new Authority(id(rs.getString("id")), rs.getString("name"), id(rs.getString("parent_id")), rs.getBoolean("is_leaf"), rs.getString("remarks")));
    }

    public void clear() {
        String sql = "DELETE FROM iam_authorities";
        jdbc.update(sql, Map.of());
    }
}
