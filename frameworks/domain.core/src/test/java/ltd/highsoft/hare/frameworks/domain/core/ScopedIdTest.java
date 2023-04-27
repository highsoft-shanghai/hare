package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScopedIdTest {

    @Test
    void should_be_able_to_carry_id_and_tenant_id() {
        assertThat(ScopedId.id("1", "t").id()).isEqualTo(Id.id("1"));
        assertThat(ScopedId.id("1", "t").tenantId()).isEqualTo(Id.id("t"));
    }

    @Test
    void should_be_able_to_compare_to_others() {
        assertThat(ScopedId.id("1", "t")).isEqualTo(ScopedId.id("1", "t"));
        assertThat(ScopedId.id("1", "t")).isNotEqualTo(ScopedId.id("2", "t"));
    }

    @Test
    void should_be_able_to_represent_empty_ids() {
        assertThat(ScopedId.EMPTY.isEmpty()).isTrue();
        assertThat(ScopedId.EMPTY.id().isEmpty()).isTrue();
        assertThat(ScopedId.EMPTY.tenantId().isEmpty()).isTrue();
    }

}
