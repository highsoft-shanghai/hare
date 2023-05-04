package ltd.highsoft.hare.foundations.iam.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import ltd.highsoft.hare.frameworks.domain.core.Id;

@AllArgsConstructor
@EqualsAndHashCode
public final class UserAccountOwner {
    private final UserOwner user;
    private final TenantOwner tenant;

    public Id userId() {
        return user.id();
    }

    public Id tenantId() {
        return tenant.id();
    }

    public User user() {
        return user.get();
    }

    public Tenant tenant() {
        return tenant.get();
    }

}

