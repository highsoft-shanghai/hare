package ltd.highsoft.frameworks.domain.core;

import java.util.*;

public interface ObjectSink {

    void put(String key, Object value);

    ObjectSink putObject(String key);

    ValueSink putArray(String key);

    Map<String, Object> toMap();

}
