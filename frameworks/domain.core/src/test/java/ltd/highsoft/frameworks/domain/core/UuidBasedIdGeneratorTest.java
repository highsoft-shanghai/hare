package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UuidBasedIdGeneratorTest {

    private IdGenerator idGenerator;

    @BeforeEach
    public void setUp() {
        idGenerator = new UuidBasedIdGenerator();
    }

    @Test
    public void should_generate_identity_correctly() {
        assertThat(idGenerator.nextId().asString().length()).isEqualTo(32);
    }

    @Test
    public void should_generate_non_duplicated_identities() {
        var id = idGenerator.nextId();
        assertThat(id).isNotEqualTo(idGenerator.nextId());
    }

    @Test
    public void should_generate_readable_identity_correctly() {
        assertThat(idGenerator.nextReadableId().asString().length()).isEqualTo(21);
        assertThat(idGenerator.nextReadableId().asString().chars().allMatch(Character::isDigit)).isTrue();
    }

    @Test
    public void should_generate_non_duplicated_readable_identities() {
        var id = idGenerator.nextReadableId();
        assertThat(idGenerator.nextReadableId()).isNotEqualTo(id);
    }

}
