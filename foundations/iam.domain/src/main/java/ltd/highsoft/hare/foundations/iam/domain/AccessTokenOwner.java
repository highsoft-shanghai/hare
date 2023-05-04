package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.context.core.SimpleUserContext;
import ltd.highsoft.hare.frameworks.context.core.UserContext;
import ltd.highsoft.hare.frameworks.domain.core.Identity;

public record AccessTokenOwner(Identity userAccount, Identity user, Identity tenant) {

    static AccessTokenOwner build(UserContext userContext) {
        return new AccessTokenOwner(userContext.userAccount(), userContext.user(), userContext.tenant());
    }

    UserContext asUserContext() {
        return new SimpleUserContext(userAccount(), user(), tenant());
    }

}
