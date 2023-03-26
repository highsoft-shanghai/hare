package ltd.highsoft.frameworks.context.core;

import lombok.*;
import ltd.highsoft.frameworks.domain.core.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GlobalUserContext {

    private static final ThreadLocal<UserContext> CONTEXT = new ThreadLocal<>();

    public static Identity currentUserAccount() {
        return context().userAccount();
    }

    public static Id currentUserAccountId() {
        return currentUserAccount().id();
    }

    public static Identity  currentUser() {
        return context().user();
    }

    public static Id currentUserId() {
        return currentUser().id();
    }

    public static Identity currentTenant() {
        return context().tenant();
    }

    public static Id currentTenantId() {
        return currentTenant().id();
    }

    public static UserContext userContext() {
        return context();
    }

    static void reset(UserContext context) {
        CONTEXT.set(context);
    }

    private static UserContext context() {
        return CONTEXT.get();
    }

}
