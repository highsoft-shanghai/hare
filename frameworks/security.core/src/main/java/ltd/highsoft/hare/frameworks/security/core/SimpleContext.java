package ltd.highsoft.hare.frameworks.security.core;

import lombok.*;
import ltd.highsoft.hare.frameworks.context.core.UserContext;

@ToString
@EqualsAndHashCode
public class SimpleContext implements Context {

    private final UserContext userContext;
    private final SecurityContext securityContext;

    public SimpleContext(UserContext userContext, SecurityContext securityContext) {
        this.userContext = userContext;
        this.securityContext = securityContext;
    }

    @Override
    public UserContext userContext() {
        return userContext;
    }

    @Override
    public SecurityContext securityContext() {
        return securityContext;
    }

}
