package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.*;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.MapsGrouper;
import ltd.highsoft.hare.frameworks.gateways.core.BeanProvider;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;

@Component
public class UserAccountMapper {

    private @Resource NamedParameterJdbcTemplate jdbc;
    private @Resource BeanProvider beanProvider;

    public void add(UserAccount userAccount) {
        String sql = "insert into iam_user_accounts(id, name, user_id, tenant_id, predefined) values(:id, :name, :user_id, :tenant_id, :predefined)";
        jdbc.update(sql, Map.of("id", userAccount.id().asString(), "name", userAccount.name(), "user_id", userAccount.owner().userId().asString(), "tenant_id", userAccount.owner().tenantId().asString(), "predefined", userAccount.predefined()));
        String sql2 = "insert into iam_user_account_roles(user_account_id, role_id) values(:user_account_id, :role_id)";
        jdbc.batchUpdate(sql2, userAccount.roles().asSet().stream().map(roleId -> Map.of("user_account_id", userAccount.id().asString(), "role_id", roleId)).toArray(Map[]::new));
    }

    public void remove(Id id) {
        String sql = "delete from iam_user_accounts where id=:id";
        jdbc.update(sql, Map.of("id", id.asString()));
        String sql2 = "delete from iam_user_account_roles where user_account_id=:user_account_id";
        jdbc.update(sql2, Map.of("user_account_id", id.asString()));
    }

    public UserAccount get(Id id) {
        String sql = "select u.id, u.name, u.user_id, u.tenant_id, u.predefined, r.role_id from iam_user_accounts u left join iam_user_account_roles r on u.id=r.user_account_id where u.id=:id";
        List<Map<String, Object>> result = jdbc.query(sql, Map.of("id", id.asString()), toMap());
        return asDomain(result).get(0);
    }

    private RowMapper<Map<String, Object>> toMap() {
        return (rs, rowNumber) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", rs.getString("id"));
            map.put("name", rs.getString("name"));
            map.put("user_id", rs.getString("user_id"));
            map.put("tenant_id", rs.getString("tenant_id"));
            map.put("role_id", rs.getString("role_id"));
            map.put("predefined", rs.getBoolean("predefined"));
            return map;
        };
    }

    private List<UserAccount> asDomain(List<Map<String, Object>> maps) {
        Map<String, List<Map<String, Object>>> grouped = MapsGrouper.of(maps).by(o -> (String) o.get("id"));
        List<UserAccount> accounts = new ArrayList<>();
        grouped.forEach((k, v) -> {
            Set<String> roleIds = v.stream().map(o -> (String) o.get("role_id")).collect(Collectors.toSet());
            Map<String, Object> map = v.get(0);
            accounts.add(new UserAccount(id((String) map.get("id")), (String) map.get("name"), new UserAccountOwner(
                    new UserOwner(id((String) map.get("user_id")), beanProvider.getBean(Users.class)),
                    new TenantOwner(id((String) map.get("tenant_id")), beanProvider.getBean(Tenants.class))),
                    new UserAccountRoles(roleIds, beanProvider.getBean(Roles.class)), (Boolean) map.get("predefined")));
        });
        return accounts;
    }

    public boolean existsByRoleId(String roleId) {
        String sql = "select count(*) from iam_user_account_roles where role_id=:role_id";
        Integer sum = jdbc.queryForObject(sql, Map.of("role_id", roleId), Integer.class);
        return Optional.ofNullable(sum).orElse(0) > 0;
    }
}
