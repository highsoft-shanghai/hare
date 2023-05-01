package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Identity;

import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;

public class DefaultIdentityFetcher implements IdentityFetcher {

    private final UserAccounts userAccounts;
    private final Users users;
    private final Tenants tenants;

    public DefaultIdentityFetcher(UserAccounts userAccounts, Users users, Tenants tenants) {
        this.userAccounts = userAccounts;
        this.users = users;
        this.tenants = tenants;
    }

    @Override
    public Identity fetchUserAccount(Id userAccountId) {
        return userAccounts.get(userAccountId).asIdentity();
    }

    // TODO: 2023/5/1
    @Override
    public Identity fetchUser(Id userId) {
//        return users.get(userId).asIdentity();
        return identity("", "");
    }

    @Override
    public Identity fetchTenant(Id tenantId) {
        return tenants.get(tenantId).asIdentity();
    }

}
