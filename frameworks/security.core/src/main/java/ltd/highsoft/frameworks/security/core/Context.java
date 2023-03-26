package ltd.highsoft.frameworks.security.core;

import ltd.highsoft.frameworks.context.core.UserContext;

public interface Context {

    Context INVALID = new SimpleContext(UserContext.INVALID, SecurityContext.INVALID);
    Context ANONYMOUS = new SimpleContext(UserContext.ANONYMOUS, SecurityContext.ANONYMOUS);
    Context SYSTEM = new SimpleContext(UserContext.SYSTEM, SecurityContext.SYSTEM);

    UserContext userContext();

    SecurityContext securityContext();

}
