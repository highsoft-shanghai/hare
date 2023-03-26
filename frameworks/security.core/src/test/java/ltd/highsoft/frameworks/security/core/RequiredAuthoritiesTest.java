package ltd.highsoft.frameworks.security.core;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RequiredAuthoritiesTest {

    @Test
    void should_be_able_to_hold_authorities() {
        assertThat(RequiredAuthorities.of("f1", "f2").asSet()).isEqualTo(Set.of("f1", "f2"));
    }

    @Test
    void should_be_able_to_represent_anonymous_requirements() {
        assertThat(RequiredAuthorities.of(Authorities.ANONYMOUS).requireAnonymous()).isTrue();
    }

    @Test
    void should_be_able_to_represent_authenticated_only_requirements() {
        assertThat(RequiredAuthorities.of().requireAuthenticatedOnly()).isTrue();
    }

    @Test
    void should_treat_normal_requirements_as_not_authenticated_only_ones() {
        assertThat(RequiredAuthorities.of("f1").requireAuthenticatedOnly()).isFalse();
    }

    @Test
    void should_be_able_to_represent_explicit_authenticated_only_requirements() {
        assertThat(RequiredAuthorities.of(Authorities.AUTHENTICATED).requireAuthenticatedOnly()).isTrue();
    }

    @Test
    void should_be_treated_as_anonymous_requirements_when_explicit_anonymous_specified() {
        assertThat(RequiredAuthorities.of(Authorities.AUTHENTICATED, Authorities.ANONYMOUS, "f1").requireAnonymous()).isTrue();
        assertThat(RequiredAuthorities.of(Authorities.AUTHENTICATED, Authorities.ANONYMOUS, "f1").requireAuthenticatedOnly()).isFalse();
    }

    @Test
    void should_be_able_to_compare_to_others_by_contents() {
        assertThat(RequiredAuthorities.of("f1", "f2")).isEqualTo(RequiredAuthorities.of("f1", "f2"));
    }

    @Test
    void should_be_able_to_format_as_simple_string() {
        assertThat(RequiredAuthorities.of("f2", "f1", "f8", "f0").toString()).isEqualTo("RequiredAuthorities(f0, f1, f2, f8)");
    }

}
