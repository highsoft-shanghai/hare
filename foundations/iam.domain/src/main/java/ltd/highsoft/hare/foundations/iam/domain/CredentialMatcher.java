package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Payload;

public interface CredentialMatcher {

    MatchResult match(Payload payload);

}
