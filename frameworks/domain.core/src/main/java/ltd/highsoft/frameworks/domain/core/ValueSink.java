package ltd.highsoft.frameworks.domain.core;

import java.util.*;

public interface ValueSink {

    void add(Object value);

    ValueSink addArray();

    ObjectSink addObject();

    List<Object> toList();

}
