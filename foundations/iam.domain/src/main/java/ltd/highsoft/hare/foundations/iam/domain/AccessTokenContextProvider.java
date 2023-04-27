package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.security.core.Context;
import ltd.highsoft.hare.frameworks.security.core.ContextProvider;

import java.util.Optional;

public class AccessTokenContextProvider implements ContextProvider {

    private final AccessTokens accessTokens;

    public AccessTokenContextProvider(AccessTokens accessTokens) {
        this.accessTokens = accessTokens;
    }

    @Override
    public Optional<Context> get(Id id) {
        return accessTokens.getOptional(id).map(x -> x);
    }

}
