package ltd.highsoft.hare.frameworks.domain.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IdentityTest {

    @Test
    void should_be_able_to_hold_id_and_name() {
        var identity = Identity.identity("john", "John");
        assertThat(identity.id()).isEqualTo(Id.id("john"));
        assertThat(identity.name()).isEqualTo("John");
    }

    @Test
    void should_be_able_to_compare_others_by_content() {
        Assertions.assertThat(Identity.identity("john", "John")).isEqualTo(Identity.identity("john", "John"));
    }

}
