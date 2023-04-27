package ltd.highsoft.hare.frameworks.domain.core;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalIdGenerator {

    private static final ThreadLocal<IdGenerator> GENERATOR = new ThreadLocal<>();

    public static Id nextId() {
        return generator().nextId();
    }

    public static Id nextReadableId() {
        return generator().nextReadableId();
    }

    static void reset(IdGenerator idGenerator) {
        GENERATOR.set(idGenerator);
    }

    private static IdGenerator generator() {
        return GENERATOR.get();
    }

}
