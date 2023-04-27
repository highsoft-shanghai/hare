package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Identity;

public interface IdentityFetcher {

    Identity fetchUserAccount(Id userAccountId);

    Identity fetchUser(Id userId);

    Identity fetchTenant(Id tenantId);

}
