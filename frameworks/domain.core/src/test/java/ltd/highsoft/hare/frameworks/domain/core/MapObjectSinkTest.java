package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class MapObjectSinkTest {

    private MapObjectSink sink;

    @BeforeEach
    void setUp() {
        sink = new MapObjectSink();
    }

    @Test
    void should_be_able_to_put_normal_values() {
        sink.put("id", "token-id");
        assertThat(sink.values()).isEqualTo(Map.of("id", "token-id"));
    }

    @Test
    void should_be_able_to_put_object_values() {
        sink.putObject("nested").put("name", "Nested");
        assertThat(sink.values()).isEqualTo(Map.of("nested", Map.of("name", "Nested")));
    }

    @Test
    void should_reject_duplicated_puts() {
        sink.put("id", "token-id");
        Throwable throwable = catchThrowable(() -> sink.put("id", "another value"));
        assertThat(throwable).isInstanceOf(BadInputException.class);
        assertThat(throwable).hasMessage("error.duplicated-key-detected: [id]");
    }

    @Test
    void should_be_able_to_put_array_values() {
        sink.putArray("names").add("John");
        assertThat(sink.values()).isEqualTo(Map.of("names", List.of("John")));
    }

}
