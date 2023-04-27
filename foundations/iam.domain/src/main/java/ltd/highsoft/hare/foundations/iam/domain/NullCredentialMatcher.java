package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Payload;

public class NullCredentialMatcher implements CredentialMatcher {

    @Override
    public MatchResult match(Payload payload) {
        return MatchResult.fail("iam.no-matched-login-type.");
    }

}
