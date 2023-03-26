package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static ltd.highsoft.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.assertThat;

class FixedIdGeneratorTest {

    public static final Id FIXED = id("fixed");

    @Test
    void should_generate_fixed_ids() {
        var idGenerator = new FixedIdGenerator(FIXED);
        assertThat(idGenerator.nextId()).isEqualTo(FIXED);
        assertThat(idGenerator.nextReadableId()).isEqualTo(FIXED);
    }

}
