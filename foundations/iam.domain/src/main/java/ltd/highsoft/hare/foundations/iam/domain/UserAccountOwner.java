package ltd.highsoft.hare.foundations.iam.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import ltd.highsoft.hare.frameworks.domain.core.Id;

@AllArgsConstructor
@EqualsAndHashCode
public final class UserAccountOwner {
    private final UserOwner userOwner;
    private final TenantOwner tenantOwner;

    public Id userId() {
        return userOwner.id();
    }

    public Id tenantId() {
        return tenantOwner.id();
    }

    public User user() {
        return userOwner.get();
    }

    public Tenant tenant() {
        return tenantOwner.get();
    }

}

