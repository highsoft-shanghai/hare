package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Payload;

import static ltd.highsoft.hare.frameworks.domain.core.I18nMessage.message;
import static ltd.highsoft.hare.frameworks.domain.core.StringFieldType.asString;

public class Login {

    private final Id id;
    private final CredentialMatcher matcher;
    private final AccessTokenFactory accessTokenFactory;
    private final AccessTokenStore accessTokenStore;

    public Login(Id id, CredentialMatcher matcher, AccessTokenFactory accessTokenFactory, AccessTokenStore accessTokenStore) {
        this.id = id;
        this.matcher = matcher;
        this.accessTokenFactory = accessTokenFactory;
        this.accessTokenStore = accessTokenStore;
    }

    public LoginResult submit(Payload payload) {
        MatchResult matchResult = matcher.match(payload);
        if (!matchResult.matched()) return LoginResult.fail(id.asString(), message("iam.username-or-password-wrong"));
        final AccessToken accessToken = accessTokenFactory.newAccessToken(matchResult.credential().owner(), payload.get("group", asString()));
        accessTokenStore.store(accessToken);
        return LoginResult.success(id.asString(), accessToken.token().asString());
    }

    public Id id() {
        return id;
    }

}
