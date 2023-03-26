package ltd.highsoft.frameworks.context.core;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GlobalUserContextResetter {

    public static void reset(UserContext context) {
        GlobalUserContext.reset(context);
    }

    public static void clear() {
        GlobalUserContext.reset(UserContext.ANONYMOUS);
    }

}
