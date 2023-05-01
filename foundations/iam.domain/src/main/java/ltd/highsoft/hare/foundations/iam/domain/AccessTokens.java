package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;

import java.util.Optional;

public interface AccessTokens {

    void add(AccessToken accessToken);

    Optional<AccessToken> getOptional(Id id);

    void remove(Id id);

    void removeAll(Id userAccountId, String group);

}
