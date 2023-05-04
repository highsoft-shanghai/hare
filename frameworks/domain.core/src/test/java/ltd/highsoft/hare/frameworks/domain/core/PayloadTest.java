package ltd.highsoft.hare.frameworks.domain.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class PayloadTest {

    @Test
    void should_convert_typed_values() {
        Payload payload = Payload.payload("a text");
        Assertions.assertThat(payload.get(StringFieldType.asString())).isEqualTo("a text");
    }

    @Test
    void should_read_typed_fields() {
        Payload payload = Payload.payload(Map.of("name", "John"));
        Assertions.assertThat(payload.get("name", StringFieldType.asString())).isEqualTo("John");
    }

    @Test
    void should_read_object_fields() {
        Payload payload = Payload.payload(Map.of("nested", Map.of("name", "John")));
        Assertions.assertThat(payload.get("nested", ObjectFieldType.asObject()).get("name", StringFieldType.asString())).isEqualTo("John");
    }

    @Test
    void should_read_objects_from_null_fields() {
        Payload payload = Payload.payload(Map.of());
        Assertions.assertThat(payload.get("nested", ObjectFieldType.asObject().nullToEmpty()).get("name", StringFieldType.asString().allowNull())).isEqualTo(null);
    }

    @Test
    void should_access_value() {
        Payload payload = Payload.payload(Map.of("name", "John"));
        assertThat(payload.value()).isNotNull();
    }

    @Test
    void should_represent_empty_state() {
        assertThat(Payload.EMPTY.value()).isEqualTo(Collections.emptyMap());
    }

    @Test
    void should_provide_full_path_info_when_get_type_errors() {
        Payload payload = Payload.payload(Map.of("nested", Map.of("name", "John")));
        Throwable throwable = catchThrowable(() -> payload.get("nested", ObjectFieldType.asObject()).get("name", DoubleFieldType.asDouble()));
        assertThat(throwable).hasMessage("error.request.field-type-mismatch: [/nested/name, DoubleFieldType, String]");
    }

    @Test
    void should_provide_full_path_info_when_get_existence_errors() {
        Payload payload = Payload.payload(Map.of("nested", Map.of("name", "John")));
        Throwable throwable = catchThrowable(() -> payload.get("nested", ObjectFieldType.asObject()).get("noting", DoubleFieldType.asDouble()));
        assertThat(throwable).hasMessage("error.request.missing-field: [/nested/noting, DoubleFieldType]");
    }

}
