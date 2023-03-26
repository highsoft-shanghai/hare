package ltd.highsoft.frameworks.test.web;

import ltd.highsoft.frameworks.context.core.SimpleUserContext;
import ltd.highsoft.frameworks.domain.core.*;
import ltd.highsoft.frameworks.security.core.*;

import java.util.Optional;

import static ltd.highsoft.frameworks.domain.core.Id.id;
import static ltd.highsoft.frameworks.domain.core.Identity.identity;

public final class GlobalTestContext {

    private static final GlobalTestContext INSTANCE = new GlobalTestContext();
    private Context context;

    public static Optional<Context> context() {
        return Optional.ofNullable(INSTANCE.context);
    }

    public static Optional<Id> token() {
        return context().map(Context::securityContext).map(SecurityContext::token);
    }

    public static void setup(GrantedAuthorities grantedAuthorities) {
        INSTANCE.context = new SimpleContext(new SimpleUserContext(userAccount(), user(), tenant()), new SimpleSecurityContext(id("tester-token-id"), grantedAuthorities));
    }

    public static void teardown() {
        INSTANCE.context = Context.ANONYMOUS;
    }

    public static Identity tenant() {
        return identity("highsoft", "Highsoft");
    }

    public static Identity user() {
        return identity("tester", "Tester");
    }

    public static Identity userAccount() {
        return identity("tester@highsoft", "Tester");
    }

}
