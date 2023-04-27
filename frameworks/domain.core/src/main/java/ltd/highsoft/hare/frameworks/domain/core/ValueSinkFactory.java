package ltd.highsoft.hare.frameworks.domain.core;

import java.util.function.Consumer;

public interface ValueSinkFactory {

    ObjectSink newObjectSink();

    ValueSink newValueSink();

    default ObjectSink newObjectSink(Consumer<ObjectSink> initializer) {
        ObjectSink sink = newObjectSink();
        initializer.accept(sink);
        return sink;
    }

    default ValueSink newValueSink(Consumer<ValueSink> initializer) {
        ValueSink sink = newValueSink();
        initializer.accept(sink);
        return sink;
    }

}
