package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;

public class UserAccountGrantedAuthoritiesResolver implements GrantedAuthoritiesResolver {

    private final UserAccounts userAccounts;

    public UserAccountGrantedAuthoritiesResolver(UserAccounts userAccounts) {
        this.userAccounts = userAccounts;
    }

    @Override
    public GrantedAuthorities resolve(Id userAccountId) {
        return userAccounts.get(userAccountId).grantedAuthorities();
    }

}
