package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Identity;

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

    @Override
    public Identity fetchUser(Id userId) {
        return users.get(userId).asIdentity();
    }

    @Override
    public Identity fetchTenant(Id tenantId) {
        return tenants.get(tenantId).asIdentity();
    }

}
