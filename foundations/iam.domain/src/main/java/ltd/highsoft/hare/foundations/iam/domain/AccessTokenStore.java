package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;

import java.util.Optional;

public class AccessTokenStore {

    private final AccessTokens accessTokens;

    public AccessTokenStore(AccessTokens accessTokens) {
        this.accessTokens = accessTokens;
    }

    public void store(AccessToken accessToken) {
        accessTokens.removeAll(accessToken.owner().userAccount().id(), accessToken.group());
        accessTokens.get(accessToken);
    }

    public Optional<AccessToken> load(Id id) {
        return accessTokens.getOptional(id);
    }

}
