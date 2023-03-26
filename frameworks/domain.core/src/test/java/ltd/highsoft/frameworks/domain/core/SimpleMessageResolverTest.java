package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleMessageResolverTest {

    private final MessageResolver MESSAGE_RESOLVER = new SimpleMessageResolver();

    @Test
    void should_resolve_message_as_code_and_data_in_string_manner() {
        assertThat(MESSAGE_RESOLVER.resolve("a-code", "a", "b")).isEqualTo("a-code: [a, b]");
    }

    @Test
    void should_resolve_message_as_code_only_if_args_is_empty() {
        assertThat(MESSAGE_RESOLVER.resolve("a-code")).isEqualTo("a-code");
    }

}
