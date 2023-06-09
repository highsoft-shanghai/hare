package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.IdGenerator;

public class LoginFactory {

    private final IdGenerator idGenerator;
    private final CredentialMatcher matcher;
    private final AccessTokenStore accessTokenStore;
    private final AccessTokenFactory accessTokenFactory;

    public LoginFactory(IdGenerator idGenerator, CredentialMatcher matcher, AccessTokenStore tokenStore, AccessTokenFactory accessTokenFactory) {
        this.idGenerator = idGenerator;
        this.matcher = matcher;
        this.accessTokenStore = tokenStore;
        this.accessTokenFactory = accessTokenFactory;
    }

    public Login create() {
        return new Login(idGenerator.nextId(), matcher, accessTokenFactory, accessTokenStore);
    }


}
