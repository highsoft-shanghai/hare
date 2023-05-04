package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Identity;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;

import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;

public record UserAccount(Id id, String name, UserAccountOwner owner, UserAccountRoles roles, boolean predefined) {

    // TODO: REFACTOR IT
    public void commit(CredentialFactory credentialFactory, UserAccounts userAccounts, Credentials credentials, Map<String, CredentialDescription> map) {
        userAccounts.add(this);
        credentials.removeForUserAccount(this.id);
        map.forEach((key, value) -> {
            if ("identity-type.username-and-password".equals(key)) {
                credentials.add(credentialFactory.newCredential(new CredentialOwner(id, Id.id(""), owner.tenantId()), CredentialType.USERNAME_AND_PASSWORD, LoginName.loginName(value.account()), EncryptedSecret.encryptedSecret(value.secret())));
            }
            // TODO: 2023/1/13
        });
    }

    public Identity asIdentity() {
        return identity(id.asString(), name);
    }

    public GrantedAuthorities grantedAuthorities() {
        return roles.grantedAuthorities();
    }

}
