package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.MalformedPayloadException;
import ltd.highsoft.hare.frameworks.domain.core.PasswordEncoder;
import ltd.highsoft.hare.frameworks.domain.core.Payload;

import java.util.HashMap;
import java.util.Map;

import static ltd.highsoft.hare.foundations.iam.domain.CredentialType.USERNAME_AND_PASSWORD;
import static ltd.highsoft.hare.foundations.iam.domain.CredentialType.credentialType;
import static ltd.highsoft.hare.frameworks.domain.core.StringFieldType.asString;

public class SelectiveCredentialMatcher implements CredentialMatcher {

    private final Map<CredentialType, CredentialMatcher> matchers = new HashMap<>();

    public SelectiveCredentialMatcher(Credentials credentials, PasswordEncoder passwordEncoder) {
        matchers.put(USERNAME_AND_PASSWORD, new UsernameAndPasswordCredentialMatcher(credentials, passwordEncoder));
    }

    @Override
    public MatchResult match(Payload payload) {
        try {
            return tryToMatch(payload);
        } catch (MalformedPayloadException e) {
            return MatchResult.fail("iam.missing-login-type");
        }
    }

    private MatchResult tryToMatch(Payload payload) {
        CredentialMatcher matcher = matchers.getOrDefault(credentialType(payload.get("type", asString())), new NullCredentialMatcher());
        return matcher.match(payload);
    }

}
