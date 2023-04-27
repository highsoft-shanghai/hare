package ltd.highsoft.hare.frameworks.security.core;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalSecurityContext {

    private static final ThreadLocal<SecurityContext> CONTEXT = ThreadLocal.withInitial(() -> SecurityContext.ANONYMOUS);

    public static SecurityContext securityContext() {
        return CONTEXT.get();
    }

    static void reset(SecurityContext context) {
        CONTEXT.set(context);
    }

    static void reset() {
        CONTEXT.set(SecurityContext.ANONYMOUS);
    }

}
