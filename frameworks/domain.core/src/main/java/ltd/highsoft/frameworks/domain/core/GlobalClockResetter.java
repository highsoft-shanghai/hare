package ltd.highsoft.frameworks.domain.core;

import lombok.*;

import java.time.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalClockResetter {

    public static void fixAt(Instant instant) {
        GlobalClock.fixedAt(instant);
    }

    public static void reset(Clock clock) {
        GlobalClock.reset(clock);
    }

    public static void reset() {
        GlobalClock.reset();
    }

}
