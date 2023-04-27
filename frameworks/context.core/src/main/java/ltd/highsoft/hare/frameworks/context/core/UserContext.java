package ltd.highsoft.hare.frameworks.context.core;

import ltd.highsoft.hare.frameworks.domain.core.Identity;

import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;

public interface UserContext {

    Identity ANONYMOUS_IDENTITY = identity("anonymous", "Anonymous");
    Identity SYSTEM_IDENTITY = identity("system", "System");
    UserContext ANONYMOUS = new SimpleUserContext(ANONYMOUS_IDENTITY, ANONYMOUS_IDENTITY, ANONYMOUS_IDENTITY);
    UserContext INVALID = new InvalidUserContext();
    UserContext SYSTEM = new SimpleUserContext(SYSTEM_IDENTITY, SYSTEM_IDENTITY, SYSTEM_IDENTITY);

    Identity userAccount();

    Identity user();

    Identity tenant();

    boolean valid();

}
