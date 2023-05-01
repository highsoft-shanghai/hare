package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.PasswordEncoder;

public class Credential {

    private final Id id;
    private final CredentialOwner credentialOwner;
    private final CredentialType type;
    private final LoginName loginName;
    private final EncryptedSecret secret;
    private final boolean predefined;

    public Credential(Id id, CredentialOwner credentialOwner, CredentialType type, LoginName loginName, EncryptedSecret secret, boolean predefined) {
        this.id = id;
        this.type = type;
        this.loginName = loginName;
        this.secret = secret;
        this.credentialOwner = credentialOwner;
        this.predefined = predefined;
    }

    public MatchResult matchSecret(PlanSecret plan, PasswordEncoder passwordEncoder) {
        return secret.match(plan, passwordEncoder) ? MatchResult.success(this) : MatchResult.fail("iam.username-or-password-wrong");
    }

    public Id id() {
        return this.id;
    }

    public CredentialOwner owner() {
        return this.credentialOwner;
    }

    public CredentialType type() {
        return type;
    }

    public LoginName loginName() {
        return this.loginName;
    }

    public EncryptedSecret secret() {
        return this.secret;
    }

    public boolean predefined() {
        return predefined;
    }

}
