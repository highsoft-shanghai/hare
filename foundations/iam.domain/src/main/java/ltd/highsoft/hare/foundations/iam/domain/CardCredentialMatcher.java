package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Payload;

import java.util.Optional;

import static ltd.highsoft.hare.frameworks.domain.core.StringFieldType.asString;

@Deprecated
public class CardCredentialMatcher implements CredentialMatcher {

    private final Credentials credentials;

    public CardCredentialMatcher(Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public MatchResult match(Payload payload) {
        return credentialOf(payload).map(this::match).orElseGet(this::fail);
    }

    private MatchResult match(Credential credential) {
        return MatchResult.success(credential);
    }

    private MatchResult fail() {
        return MatchResult.fail("iam.user-does-not-exist");
    }

    private Optional<Credential> credentialOf(Payload payload) {
        return credentials.credentialFor(CredentialType.CARD, payload.get("cardNumber", asString()));
    }

}
