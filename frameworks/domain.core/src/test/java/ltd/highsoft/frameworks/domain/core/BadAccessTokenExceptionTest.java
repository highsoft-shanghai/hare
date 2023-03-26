package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BadAccessTokenExceptionTest {

    @Test
    void should_be_able_to_represent_bad_token_errors() {
        assertThat(new BadAccessTokenException()).isInstanceOf(DomainException.class).hasMessage("error.bad-token");
    }

}
