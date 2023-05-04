package ltd.highsoft.hare.frameworks.security.core;

import ltd.highsoft.hare.frameworks.domain.core.AuthenticationException;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GrantedAuthoritiesTest {

    @Test
    void should_be_able_to_hold_authorities() {
        assertThat(GrantedAuthorities.of("f1", "f2").asSet()).isEqualTo(Set.of("f1", "f2"));
        assertThat(GrantedAuthorities.of(Set.of("f1", "f2")).asSet()).isEqualTo(Set.of("f1", "f2"));
    }

    @Test
    void should_be_able_to_load_from_comma_separated_values_string() {
        assertThat(GrantedAuthorities.fromCommaSeparatedString("f1,f2").asSet()).isEqualTo(Set.of("f1", "f2"));
        assertThat(GrantedAuthorities.fromCommaSeparatedString("f1, f2").asSet()).isEqualTo(Set.of("f1", "f2"));
    }

    @Test
    void should_allow_authorized_accesses() {
        assertDoesNotThrow(() -> GrantedAuthorities.of("f1", "f2").authorize(RequiredAuthorities.of("f2")));
        assertDoesNotThrow(() -> GrantedAuthorities.of("f1", "f2").authorize(RequiredAuthorities.of("f1")));
    }

    @Test
    void should_deny_unauthorized_accesses() {
        var throwable = catchThrowable(() -> GrantedAuthorities.of("f1", "f2").authorize(RequiredAuthorities.of("f3")));
        assertThat(throwable).isInstanceOf(AuthorizationException.class);
        assertThat(throwable).hasMessage("error.access-denied: [RequiredAuthorities(f3), GrantedAuthorities(f1, f2)]");
    }

    @Test
    void should_allow_accesses_which_just_require_anonymous() {
        assertDoesNotThrow(() -> GrantedAuthorities.of("f1", "f2").authorize(RequiredAuthorities.ANONYMOUS));
        assertDoesNotThrow(() -> GrantedAuthorities.of().authorize(RequiredAuthorities.ANONYMOUS));
    }

    @Test
    void should_allow_accesses_which_just_require_authenticated() {
        assertDoesNotThrow(() -> GrantedAuthorities.of("f1", "f2").authorize(RequiredAuthorities.AUTHENTICATED));
    }

    @Test
    void should_allow_any_accesses_when_it_contains_admin_authority() {
        assertDoesNotThrow(() -> GrantedAuthorities.of(Authorities.ADMIN).authorize(RequiredAuthorities.of("any-access")));
    }

    @Test
    void should_allow_any_accesses_when_it_contains_system_authority() {
        assertDoesNotThrow(() -> GrantedAuthorities.of(Authorities.SYSTEM).authorize(RequiredAuthorities.of("any-access")));
    }

    @Test
    void should_deny_accesses_which_require_more_than_anonymous_if_it_is_anonymous() {
        Throwable throwable = catchThrowable(() -> GrantedAuthorities.ANONYMOUS.authorize(RequiredAuthorities.of("f3")));
        assertThat(throwable).isInstanceOf(AuthenticationException.class);
        assertThat(throwable).hasMessage("error.authentication-required");
    }

    @Test
    void should_be_able_to_format_as_simple_string() {
        assertThat(GrantedAuthorities.of("f1", "f2", "f8", "f0").toString()).isEqualTo("GrantedAuthorities(f0, f1, f2, f8)");
    }

    @Test
    void should_be_able_to_save_as_comma_separated_value_strings() {
        assertThat(GrantedAuthorities.of("f1", "f2", "f8", "f0").toCommaSeparatedString()).isEqualTo("f1,f2,f8,f0");
    }

    @Test
    void should_be_able_to_merge_from_others() {
        assertThat(GrantedAuthorities.of(Stream.of(GrantedAuthorities.of("f1"), GrantedAuthorities.of("f2")))).isEqualTo(GrantedAuthorities.of("f1", "f2"));
    }

}
