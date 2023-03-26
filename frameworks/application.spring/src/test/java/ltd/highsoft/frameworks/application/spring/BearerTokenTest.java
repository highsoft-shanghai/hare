package ltd.highsoft.frameworks.application.spring;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BearerTokenTest {

    @Test
    void should_be_able_to_parse_bearer_token() {
        assertThat(new BearerToken("Bearer token-id").value()).isEqualTo("token-id");
    }

    @Test
    void should_parse_non_bearer_token_as_invalid_token() {
        assertThat(new BearerToken("token-id").value()).isNull();
    }

    @Test
    void should_parse_null_token_as_invalid_token() {
        assertThat(new BearerToken(null).value()).isNull();
    }

    @Test
    void should_parse_prefix_only_token_as_invalid_token() {
        assertThat(new BearerToken(BearerToken.BEARER).value()).isNull();
    }

}
