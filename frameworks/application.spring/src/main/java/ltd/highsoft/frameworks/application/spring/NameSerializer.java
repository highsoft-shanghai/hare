package ltd.highsoft.frameworks.application.spring;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import ltd.highsoft.frameworks.domain.core.Name;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class NameSerializer extends JsonSerializer<Name> {

    @Override
    public void serialize(Name value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.asString());
    }

}
