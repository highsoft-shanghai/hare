package ltd.highsoft.hare.frameworks.domain.core;

import lombok.*;

import java.time.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalClock {

    public static final ZoneId DEFAULT_TIME_ZONE = ZoneId.of("Asia/Shanghai");
    private static Clock clock = Clock.system(DEFAULT_TIME_ZONE);

    public static Instant now() {
        return clock().instant();
    }

    public static ZonedDateTime localNow() {
        return ZonedDateTime.now(clock());
    }

    public static ZoneId zone() {
        return clock().getZone();
    }

    static void reset(Clock clock) {
        GlobalClock.clock = clock;
    }

    static void fixedAt(Instant instant) {
        reset(Clock.fixed(instant, DEFAULT_TIME_ZONE));
    }

    static void reset() {
        reset(Clock.system(DEFAULT_TIME_ZONE));
    }

    private static Clock clock() {
        return GlobalClock.clock;
    }

}
