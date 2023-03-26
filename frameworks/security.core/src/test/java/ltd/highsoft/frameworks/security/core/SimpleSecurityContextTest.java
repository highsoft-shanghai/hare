package ltd.highsoft.frameworks.security.core;

import ltd.highsoft.frameworks.domain.core.*;
import org.junit.jupiter.api.Test;

import static ltd.highsoft.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.*;

class SimpleSecurityContextTest {

    private static final Id ID = id("simple");

    @Test
    void should_answer_yes_when_test_for_validity() {
        assertThat(new SimpleSecurityContext(ID, GrantedAuthorities.ANONYMOUS).valid()).isTrue();
    }

    @Test
    void should_be_able_to_hold_granted_authorities() {
        var context = new SimpleSecurityContext(ID, GrantedAuthorities.ANONYMOUS);
        assertThat(context.token()).isEqualTo(ID);
        assertThat(context.grantedAuthorities()).isEqualTo(GrantedAuthorities.ANONYMOUS);
    }

    @Test
    void should_delegate_authorization_to_underlying_granted_authorities() {
        var context = new SimpleSecurityContext(ID, GrantedAuthorities.ANONYMOUS);
        assertThatThrownBy(() -> context.authorize(RequiredAuthorities.AUTHENTICATED)).isInstanceOf(AuthenticationException.class);
    }

}
