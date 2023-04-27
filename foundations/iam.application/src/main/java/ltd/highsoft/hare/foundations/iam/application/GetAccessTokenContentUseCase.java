package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.AccessTokens;
import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.domain.core.ObjectSink;
import ltd.highsoft.hare.frameworks.domain.core.ValueSinkFactory;
import ltd.highsoft.hare.frameworks.security.core.Authorities;

import java.util.Optional;

import static ltd.highsoft.hare.frameworks.security.core.GlobalSecurityContext.securityContext;

@UseCase(requiredAuthorities = Authorities.AUTHENTICATED)
public class GetAccessTokenContentUseCase {

    private final AccessTokens accessTokens;
    private final ValueSinkFactory valueSinkFactory;

    public GetAccessTokenContentUseCase(AccessTokens accessTokens, ValueSinkFactory valueSinkFactory) {
        this.accessTokens = accessTokens;
        this.valueSinkFactory = valueSinkFactory;
    }

    public Optional<ObjectSink> execute() {
        var accessToken = accessTokens.getOptional(securityContext().token());
        return accessToken.map(token -> valueSinkFactory.newObjectSink(token::content));
    }

}

