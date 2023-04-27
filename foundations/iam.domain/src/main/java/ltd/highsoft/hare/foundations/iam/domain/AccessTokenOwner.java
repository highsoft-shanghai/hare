package ltd.highsoft.hare.foundations.iam.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ltd.highsoft.hare.frameworks.context.core.SimpleUserContext;
import ltd.highsoft.hare.frameworks.context.core.UserContext;
import ltd.highsoft.hare.frameworks.domain.core.Identity;

@ToString
@EqualsAndHashCode
public class AccessTokenOwner {

    private final Identity userAccount;
    private final Identity user;
    private final Identity tenant;

    public AccessTokenOwner(Identity userAccount, Identity user, Identity tenant) {
        this.userAccount = userAccount;
        this.user = user;
        this.tenant = tenant;
    }

    public AccessTokenOwner(UserContext userContext) {
        this.userAccount = userContext.userAccount();
        this.user = userContext.user();
        this.tenant = userContext.tenant();
    }

    public Identity userAccount() {
        return userAccount;
    }

    public Identity user() {
        return user;
    }

    public Identity tenant() {
        return tenant;
    }

    public UserContext asUserContext() {
        return new SimpleUserContext(userAccount(), user(), tenant());
    }

}
