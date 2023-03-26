package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static ltd.highsoft.frameworks.domain.core.Id.id;
import static ltd.highsoft.frameworks.domain.core.Identity.identity;
import static org.assertj.core.api.Assertions.assertThat;

class IdentityTest {

    @Test
    void should_be_able_to_hold_id_and_name() {
        var identity = identity("john", "John");
        assertThat(identity.id()).isEqualTo(id("john"));
        assertThat(identity.name()).isEqualTo("John");
    }

    @Test
    void should_be_able_to_compare_others_by_content() {
        assertThat(identity("john", "John")).isEqualTo(identity("john", "John"));
    }

}
