package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Identity;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;

public final class TestAccessTokens {

    public static final AccessTokenOwner TOKEN_OWNER_OF_TESTER = new AccessTokenOwner(Identity.identity("tester@highsoft.ltd", "Test"), Identity.identity("tester", "Tester"), Identity.identity("highsoft", "Highsoft"));
    public static final AccessToken ACCESS_TOKEN_OF_TESTER = new AccessToken(Id.id("token-id"), TOKEN_OWNER_OF_TESTER, GrantedAuthorities.of("f1"), "web");

}
