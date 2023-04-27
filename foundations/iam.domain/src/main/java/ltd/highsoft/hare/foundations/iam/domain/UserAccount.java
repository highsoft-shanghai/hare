package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Identity;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;

import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;

public class UserAccount {

    private final Id id;
    private final String name;
    private final UserAccountOwner userAccountOwner;
    private final UserAccountRoles roles;
    private final boolean predefined;

    public UserAccount(Id id, String name, UserAccountOwner owner, UserAccountRoles roles, boolean predefined) {
        this.id = id;
        this.name = name;
        this.userAccountOwner = owner;
        this.roles = roles;
        this.predefined = predefined;
    }

    public void commit(CredentialFactory credentialFactory, UserAccounts userAccounts, Credentials credentials, Map<String, CredentialDescription> map) {
        userAccounts.add(this);
        credentials.removeForUserAccount(this.id);
        map.forEach((key, value) -> {
            if ("identity-type.card".equals(key)) {
                credentials.add(credentialFactory.newCredential(new CredentialOwner(id, Id.id(""), userAccountOwner.tenantId()), CredentialType.CARD, LoginName.loginName(value.account()), new EncryptedSecret("")));
            }
            if ("identity-type.face".equals(key)) {
                credentials.add(credentialFactory.newCredential(new CredentialOwner(id, Id.id(""), userAccountOwner.tenantId()), CredentialType.FACE, LoginName.loginName(value.account()), new EncryptedSecret("")));
            }
            if ("identity-type.username-and-password".equals(key)) {
                credentials.add(credentialFactory.newCredential(new CredentialOwner(id, Id.id(""), userAccountOwner.tenantId()), CredentialType.USERNAME_AND_PASSWORD, LoginName.loginName(value.account()), EncryptedSecret.encryptedSecret(value.secret())));
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

    public Id id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public UserAccountOwner owner() {
        return this.userAccountOwner;
    }

    public UserAccountRoles roles() {
        return roles;
    }

    public boolean predefined() {
        return predefined;
    }

}
