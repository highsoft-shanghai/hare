package ltd.highsoft.hare.foundations.iam.domain;

import lombok.*;
import ltd.highsoft.hare.frameworks.domain.core.Id;

@ToString
@EqualsAndHashCode
public class CredentialOwner {

    private final Id userAccountId;
    private final Id userId;
    private final Id tenantId;

    public CredentialOwner(Id userAccountId, Id userId, Id tenantId) {
        this.userAccountId = userAccountId;
        this.userId = userId;
        this.tenantId = tenantId;
    }

    public Id userAccountId() {
        return userAccountId;
    }

    public Id userId() {
        return userId;
    }

    public Id tenantId() {
        return tenantId;
    }

}
