package ltd.highsoft.hare.frameworks.application.spring;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import ltd.highsoft.hare.frameworks.domain.core.Payload;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.LinkedHashMap;

import static ltd.highsoft.hare.frameworks.domain.core.Payload.payload;

@JsonComponent
public class PayloadDeserializer extends JsonDeserializer<Payload> {

    @Override
    public Payload deserialize(JsonParser p, DeserializationContext context) throws IOException {
        return payload(p.readValueAs(LinkedHashMap.class));
    }

}
