package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class ListValueSinkTest {

    private ListValueSink sink;

    @BeforeEach
    void setUp() {
        sink = new ListValueSink();
    }

    @Test
    void should_be_able_to_add_normal_values() {
        sink.add("test");
        assertThat(sink.toList()).isEqualTo(List.of("test"));
    }

    @Test
    void should_be_able_to_add_array_values() {
        sink.addArray().add("test");
        assertThat(sink.toList()).isEqualTo(List.of(List.of("test")));
    }

    @Test
    void should_be_able_to_add_object_values() {
        sink.addObject().put("test", "Test");
        assertThat(sink.toList()).isEqualTo(List.of(Map.of("test", "Test")));
    }

}
