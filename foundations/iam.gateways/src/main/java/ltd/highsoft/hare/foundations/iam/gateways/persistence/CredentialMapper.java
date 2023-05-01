package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.Credential;
import ltd.highsoft.hare.foundations.iam.domain.CredentialOwner;
import ltd.highsoft.hare.foundations.iam.domain.CredentialType;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ltd.highsoft.hare.foundations.iam.domain.CredentialType.credentialType;
import static ltd.highsoft.hare.foundations.iam.domain.EncryptedSecret.encryptedSecret;
import static ltd.highsoft.hare.foundations.iam.domain.LoginName.loginName;
import static ltd.highsoft.hare.frameworks.domain.core.Id.id;

@Component
public class CredentialMapper {

    private @Resource NamedParameterJdbcTemplate jdbc;


    public Optional<Credential> credentialFor(CredentialType type, String loginName) {
        String sql = "SELECT id, type, login_name, secret, user_account_id, user_id, tenant_id, predefined FROM iam_credentials WHERE type = :type AND login_name = :loginName";
        return jdbc.query(sql, Map.of("type", type.asString(), "loginName", loginName), (rs, rowNum) -> asDomain(rs)).stream().findFirst();
    }

    public void add(Credential credential) {
        String sql = "INSERT INTO iam_credentials (id, type, login_name, secret, user_account_id, user_id, tenant_id, predefined) VALUES (:id, :type, :loginName, :secret, :userAccountId, :userId, :tenantId, :predefined)";
        jdbc.update(sql, Map.of("id", credential.id().asString(), "type", credential.type().asString(), "loginName", credential.loginName().asString(),
                "secret", credential.secret().asString(), "userAccountId", credential.owner().userAccountId().asString(), "userId", credential.owner().userId().asString(), "tenantId",
                credential.owner().tenantId().asString(), "predefined", credential.predefined()));
    }

    public List<Credential> getForUserAccount(Id userAccountId) {
        String sql = "SELECT id, type, login_name, secret, user_account_id, user_id, tenant_id, predefined FROM iam_credentials WHERE user_account_id = :userAccountId";
        return jdbc.query(sql, Map.of("userAccountId", userAccountId.asString()), (rs, rowNum) -> asDomain(rs));
    }

    private static Credential asDomain(ResultSet rs) throws SQLException {
        return new Credential(id(rs.getString("id")), new CredentialOwner(id(rs.getString("user_account_id")), id(rs.getString("user_id")), id(rs.getString("tenant_id"))),
                credentialType(rs.getString("type")), loginName(rs.getString("login_name")), encryptedSecret(rs.getString("secret")), rs.getBoolean("predefined"));
    }

    public void remove(Id id) {
        String sql = "DELETE FROM iam_credentials WHERE id = :id";
        jdbc.update(sql, Map.of("id", id.asString()));
    }

    public void removeForUserAccount(Id userAccountId) {
        String sql = "DELETE FROM iam_credentials WHERE user_account_id = :userAccountId";
        jdbc.update(sql, Map.of("userAccountId", userAccountId.asString()));
    }
}
