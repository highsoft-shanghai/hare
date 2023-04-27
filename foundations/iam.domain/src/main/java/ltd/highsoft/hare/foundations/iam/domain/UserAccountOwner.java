package ltd.highsoft.hare.foundations.iam.domain;

import lombok.*;
import ltd.highsoft.hare.frameworks.domain.core.Id;

@ToString
@EqualsAndHashCode
public class UserAccountOwner {

    private final Id userId;
    private final Id tenantId;

    public UserAccountOwner(Id userId, Id tenantId) {
        this.userId = userId;
        this.tenantId = tenantId;
    }

    public Id userId() {
        return userId;
    }

    public Id tenantId() {
        return tenantId;
    }

}
