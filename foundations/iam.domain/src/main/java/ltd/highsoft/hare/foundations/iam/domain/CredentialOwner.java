package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;

public record CredentialOwner(Id userAccountId, Id userId, Id tenantId) {

}
