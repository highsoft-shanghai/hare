package ltd.highsoft.frameworks.application.spring;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import ltd.highsoft.frameworks.domain.core.Code;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class CodeSerializer extends JsonSerializer<Code> {

    @Override
    public void serialize(Code value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.asString());
    }

}
