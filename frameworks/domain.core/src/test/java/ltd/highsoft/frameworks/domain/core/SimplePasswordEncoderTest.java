package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimplePasswordEncoderTest {

    @Test
    void should_be_able_to_match_passwords_without_encoding() {
        var passwordEncoder = new SimplePasswordEncoder();
        assertThat(passwordEncoder.matches("a", "b")).isFalse();
        assertThat(passwordEncoder.matches("a", "a")).isTrue();
    }

    @Test
    void should_be_able_to_password_encoding() {
        var passwordEncoder = new SimplePasswordEncoder();
        assertThat(passwordEncoder.encode("a").matches(passwordEncoder.encode("a"))).isTrue();
        assertThat(passwordEncoder.encode("a").matches(passwordEncoder.encode("b"))).isFalse();
    }

}
