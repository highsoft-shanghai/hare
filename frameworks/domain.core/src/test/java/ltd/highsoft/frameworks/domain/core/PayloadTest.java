package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import java.util.*;

import static ltd.highsoft.frameworks.domain.core.DoubleFieldType.asDouble;
import static ltd.highsoft.frameworks.domain.core.ObjectFieldType.asObject;
import static ltd.highsoft.frameworks.domain.core.Payload.payload;
import static ltd.highsoft.frameworks.domain.core.StringFieldType.asString;
import static org.assertj.core.api.Assertions.*;

public class PayloadTest {

    @Test
    void should_be_able_to_convert_typed_values() {
        Payload payload = payload("a text");
        assertThat(payload.get(asString())).isEqualTo("a text");
    }

    @Test
    void should_be_able_to_read_typed_fields() {
        Payload payload = payload(Map.of("name", "John"));
        assertThat(payload.get("name", asString())).isEqualTo("John");
    }

    @Test
    void should_be_able_to_read_object_fields() {
        Payload payload = payload(Map.of("nested", Map.of("name", "John")));
        assertThat(payload.get("nested", asObject()).get("name", asString())).isEqualTo("John");
    }

    @Test
    void should_be_able_to_read_objects_from_null_fields() {
        Payload payload = payload(Map.of());
        assertThat(payload.get("nested", asObject().nullToEmpty()).get("name", asString().allowNull())).isEqualTo(null);
    }

    @Test
    void should_be_able_to_access_value() {
        Payload payload = payload(Map.of("name", "John"));
        assertThat(payload.value()).isNotNull();
    }

    @Test
    void should_be_able_to_represent_empty_state() {
        assertThat(Payload.EMPTY.value()).isEqualTo(Collections.emptyMap());
    }

    @Test
    void should_provide_full_path_info_when_get_type_errors() {
        Payload payload = payload(Map.of("nested", Map.of("name", "John")));
        Throwable throwable = catchThrowable(() -> payload.get("nested", asObject()).get("name", asDouble()));
        assertThat(throwable).hasMessage("error.request.field-type-mismatch: [/nested/name, DoubleFieldType, String]");
    }

    @Test
    void should_provide_full_path_info_when_get_existence_errors() {
        Payload payload = payload(Map.of("nested", Map.of("name", "John")));
        Throwable throwable = catchThrowable(() -> payload.get("nested", asObject()).get("noting", asDouble()));
        assertThat(throwable).hasMessage("error.request.missing-field: [/nested/noting, DoubleFieldType]");
    }

}
