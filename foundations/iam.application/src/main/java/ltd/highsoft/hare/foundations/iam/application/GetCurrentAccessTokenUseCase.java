package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.AccessTokens;
import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.security.core.Authorities;

import java.util.Optional;

import static ltd.highsoft.hare.frameworks.security.core.GlobalSecurityContext.securityContext;

@UseCase(requiredAuthorities = Authorities.AUTHENTICATED)
public class GetCurrentAccessTokenUseCase {

    private final AccessTokens accessTokens;

    public GetCurrentAccessTokenUseCase(AccessTokens accessTokens) {
        this.accessTokens = accessTokens;
    }

    public Optional<AccessTokenContent> execute() {
        return accessTokens.getOptional(securityContext().token()).map(AccessTokenContent::build);
    }

}

