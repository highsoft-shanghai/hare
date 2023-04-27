package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultValueSinkFactoryTest {

    @Test
    void should_be_able_to_create_object_sinks() {
        var factory = new DefaultValueSinkFactory();
        ObjectSink sink = factory.newObjectSink();
        sink.put("test", "Test");
        assertThat(sink.toMap()).isEqualTo(Map.of("test", "Test"));
    }

    @Test
    void should_be_able_to_create_value_sinks() {
        var factory = new DefaultValueSinkFactory();
        ValueSink sink = factory.newValueSink();
        sink.add("test");
        assertThat(sink.toList()).isEqualTo(List.of("test"));
    }

    @Test
    void should_be_able_to_create_value_sinks_with_data() {
        var factory = new DefaultValueSinkFactory();
        assertThat(factory.newValueSink(sink -> sink.add("test")).toList()).isEqualTo(List.of("test"));
    }

    @Test
    void should_be_able_to_create_object_sinks_with_data() {
        var factory = new DefaultValueSinkFactory();
        assertThat(factory.newObjectSink(sink -> sink.put("name", "John")).toMap()).isEqualTo(Map.of("name", "John"));
    }

}
