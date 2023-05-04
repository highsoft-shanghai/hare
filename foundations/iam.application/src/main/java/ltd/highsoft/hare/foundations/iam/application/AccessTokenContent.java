package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.AccessToken;
import ltd.highsoft.hare.frameworks.domain.core.Id;

import java.util.Set;

public record AccessTokenContent(Id accessToken, Set<String> authorities, String userAccountName, String tenantName) {

    public static AccessTokenContent build(AccessToken token) {
        return new AccessTokenContent(token.token(), token.grantedAuthorities().asSet(), token.owner().userAccount().name(), token.owner().tenant().name());
    }

}
