package ltd.highsoft.hare.frameworks.domain.core;

import java.util.*;

public class ListValueSink implements ValueSink {

    private final List<Object> values = new ArrayList<>();

    public List<Object> values() {
        return values;
    }

    @Override
    public void add(Object value) {
        values.add(value);
    }

    @Override
    public ValueSink addArray() {
        ListValueSink array = new ListValueSink();
        values.add(array.values());
        return array;
    }

    @Override
    public ObjectSink addObject() {
        MapObjectSink object = new MapObjectSink();
        values.add(object.values());
        return object;
    }

    @Override
    public List<Object> toList() {
        return Collections.unmodifiableList(values);
    }

}
