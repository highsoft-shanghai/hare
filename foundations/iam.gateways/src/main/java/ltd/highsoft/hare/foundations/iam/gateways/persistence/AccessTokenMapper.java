package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.AccessToken;
import ltd.highsoft.hare.foundations.iam.domain.AccessTokenOwner;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;

@Component
public class AccessTokenMapper {

    private @Resource NamedParameterJdbcTemplate jdbc;


    public Optional<AccessToken> getOptional(Id id) {
        String sql = "SELECT id, user_account_id, user_account_name, user_id, user_name, tenant_id, tenant_name, granted_authorities, token_group FROM iam_access_tokens WHERE id = :id";
        return jdbc.query(sql, Map.of("id", id.asString()), (rs, rowNum) -> new AccessToken(id(rs.getString("id")), new AccessTokenOwner(identity(rs.getString("user_account_id"), rs.getString("user_account_name")),
                identity(rs.getString("user_id"), rs.getString("user_name")), identity(rs.getString("tenant_id"), rs.getString("tenant_name"))),
                GrantedAuthorities.fromCommaSeparatedString(rs.getString("granted_authorities")), rs.getString("token_group"))).stream().findFirst();
    }

    public void add(AccessToken accessToken) {
        String sql = "INSERT INTO iam_access_tokens (id, user_account_id, user_account_name, user_id, user_name, tenant_id, tenant_name, granted_authorities, token_group) " +
                "VALUES (:id, :user_account_id, :user_account_name, :user_id, :user_name, :tenant_id, :tenant_name, :granted_authorities, :token_group)";
        jdbc.update(sql, Map.of("id", accessToken.token().asString(), "user_account_id", accessToken.owner().userAccount().id().asString(), "user_account_name", accessToken.owner().userAccount().name(),
                "user_id", accessToken.owner().user().id().asString(), "user_name", accessToken.owner().user().name(),
                "tenant_id", accessToken.owner().tenant().id().asString(), "tenant_name", accessToken.owner().tenant().name(), "granted_authorities", accessToken.grantedAuthorities().toCommaSeparatedString(), "token_group", accessToken.group()));
    }

    public void remove(Id id) {
        String sql = "DELETE FROM iam_access_tokens WHERE id = :id";
        jdbc.update(sql, Map.of("id", id.asString()));
    }

    public void removeAll(Id userAccountId, String group) {
        String sql = "DELETE FROM iam_access_tokens WHERE user_account_id = :user_account_id AND token_group = :token_group";
        jdbc.update(sql, Map.of("user_account_id", userAccountId.asString(), "token_group", group));
    }
}
