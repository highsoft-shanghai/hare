package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.PasswordEncoder;

public record Credential(Id id, CredentialOwner owner, CredentialType type, LoginName loginName, EncryptedSecret secret, boolean predefined) {

    public MatchResult matchSecret(PlanSecret plan, PasswordEncoder passwordEncoder) {
        return secret.match(plan, passwordEncoder) ? MatchResult.success(this) : MatchResult.fail("iam.username-or-password-wrong");
    }

}
