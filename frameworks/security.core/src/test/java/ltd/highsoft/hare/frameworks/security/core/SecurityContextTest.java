package ltd.highsoft.hare.frameworks.security.core;

import org.junit.jupiter.api.Test;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.assertThat;

class SecurityContextTest {

    @Test
    void should_represent_anonymous() {
        assertThat(SecurityContext.ANONYMOUS.token()).isEqualTo(id("anonymous"));
        assertThat(SecurityContext.ANONYMOUS.grantedAuthorities()).isEqualTo(GrantedAuthorities.ANONYMOUS);
    }

    @Test
    void should_represent_system() {
        assertThat(SecurityContext.SYSTEM.token()).isEqualTo(id("system"));
        assertThat(SecurityContext.SYSTEM.grantedAuthorities()).isEqualTo(GrantedAuthorities.SYSTEM);
    }

    @Test
    void should_represent_invalid_context() {
        assertThat(SecurityContext.INVALID.valid()).isFalse();
    }

}
