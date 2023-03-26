package ltd.highsoft.frameworks.domain.core;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalIdGeneratorResetter {

    public static void reset(IdGenerator idGenerator) {
        GlobalIdGenerator.reset(idGenerator);
    }

}
