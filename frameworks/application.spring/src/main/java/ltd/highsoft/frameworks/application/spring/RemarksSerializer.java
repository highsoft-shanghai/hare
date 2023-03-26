package ltd.highsoft.frameworks.application.spring;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import ltd.highsoft.frameworks.domain.core.Remarks;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class RemarksSerializer extends JsonSerializer<Remarks> {

    @Override
    public void serialize(Remarks value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.asString());
    }

}
