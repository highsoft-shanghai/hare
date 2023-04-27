package ltd.highsoft.hare.frameworks.application.spring;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import ltd.highsoft.hare.frameworks.domain.core.ValueSink;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class ValueSinkSerializer extends JsonSerializer<ValueSink> {

    @Override
    public void serialize(ValueSink value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (Object o : value.toList()) {
            gen.writeObject(o);
        }
        gen.writeEndArray();
    }

}
