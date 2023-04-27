package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import ltd.highsoft.hare.foundations.iam.domain.Credential;
import ltd.highsoft.hare.foundations.iam.domain.CredentialOwner;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.foundations.iam.domain.CredentialType.credentialType;
import static ltd.highsoft.hare.foundations.iam.domain.EncryptedSecret.encryptedSecret;
import static ltd.highsoft.hare.foundations.iam.domain.LoginName.loginName;

@Entity(name = "iam_credentials")
@NoArgsConstructor
public class JpaCredential {

    private @Id String id;
    private @Column(name = "type") String type;
    private @Column(name = "login_name") String loginName;
    private @Column(name = "secret") String secret;
    private @Column(name = "user_account_id") String userAccountId;
    private @Column(name = "user_id") String userId;
    private @Column(name = "tenant_id") String tenantId;
    private @Column(name = "predefined") Boolean predefined;

    public JpaCredential(Credential credential) {
        this.id = credential.id().asString();
        this.type = credential.type().asString();
        this.loginName = credential.loginName().asString();
        this.secret = credential.secret().asString();
        this.userAccountId = credential.owner().userAccountId().asString();
        this.userId = credential.owner().userId().asString();
        this.tenantId = credential.owner().tenantId().asString();
        this.predefined = credential.predefined();
    }

    public Credential asDomain() {
        return new Credential(
            id(id), new CredentialOwner(id(userAccountId), id(userId), id(tenantId)), credentialType(type), loginName(loginName), encryptedSecret(secret),
            predefined
        );
    }

}
