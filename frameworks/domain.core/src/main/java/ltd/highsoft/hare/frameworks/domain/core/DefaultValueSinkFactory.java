package ltd.highsoft.hare.frameworks.domain.core;

public class DefaultValueSinkFactory implements ValueSinkFactory {

    @Override
    public ObjectSink newObjectSink() {
        return new MapObjectSink();
    }

    @Override
    public ValueSink newValueSink() {
        return new ListValueSink();
    }

}
