package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.PasswordEncoder;
import ltd.highsoft.hare.frameworks.domain.core.Payload;

import java.util.Optional;

import static ltd.highsoft.hare.frameworks.domain.core.StringFieldType.asString;

public class UsernameAndPasswordCredentialMatcher implements CredentialMatcher {

    private final Credentials credentials;
    private final PasswordEncoder passwordEncoder;

    public UsernameAndPasswordCredentialMatcher(Credentials credentials, PasswordEncoder passwordEncoder) {
        this.credentials = credentials;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MatchResult match(Payload payload) {
        return credentialOf(payload).map(x -> match(x, payload)).orElseGet(this::fail);
    }

    private MatchResult match(Credential credential, Payload payload) {
        return credential.matchSecret(PlanSecret.from(payload.get("password", asString())), passwordEncoder);
    }

    // TODO
    private MatchResult fail() {
        return MatchResult.fail("iam.username-or-password-wrong");
    }

    private Optional<Credential> credentialOf(Payload payload) {
        return credentials.credentialFor(CredentialType.USERNAME_AND_PASSWORD, payload.get("username", asString()));
    }

}
