package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import ltd.highsoft.hare.foundations.iam.domain.AccessToken;
import ltd.highsoft.hare.foundations.iam.domain.AccessTokenOwner;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;

@Entity(name = "iam_access_tokens")
@NoArgsConstructor
public class JpaAccessToken {

    private @Id String id;
    private @Column(name = "user_account_id") String userAccountId;
    private @Column(name = "user_account_name") String userAccountName;
    private @Column(name = "user_id") String userId;
    private @Column(name = "user_name") String userName;
    private @Column(name = "tenant_id") String tenantId;
    private @Column(name = "tenant_name") String tenantName;
    private @Column(name = "granted_authorities") String grantedAuthorities;
    private @Column(name = "token_group") String group;

    public JpaAccessToken(AccessToken accessToken) {
        this.id = accessToken.token().asString();
        this.userAccountId = accessToken.owner().userAccount().id().asString();
        this.userAccountName = accessToken.owner().userAccount().name();
        this.userId = accessToken.owner().user().id().asString();
        this.userName = accessToken.owner().user().name();
        this.tenantId = accessToken.owner().tenant().id().asString();
        this.tenantName = accessToken.owner().tenant().name();
        this.grantedAuthorities = accessToken.grantedAuthorities().toCommaSeparatedString();
        this.group = accessToken.group();
    }

    public AccessToken asDomain() {
        return new AccessToken(
                id(id), new AccessTokenOwner(identity(userAccountId, userAccountName), identity(userId, userName), identity(tenantId, tenantName)),
                GrantedAuthorities.fromCommaSeparatedString(grantedAuthorities), group
        );
    }

}
