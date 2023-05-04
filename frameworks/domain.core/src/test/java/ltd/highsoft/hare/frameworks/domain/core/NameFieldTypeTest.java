package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameFieldTypeTest {

    @Test
    void should_construct_names_from_strings() {
        assertThat(NameFieldType.asName().from("John", "")).isEqualTo(Name.name("John"));
    }

    @Test
    void should_reject_type_mismatched_inputs() {
        Throwable caught = catchThrowable(() -> NameFieldType.asName().from(1, "/"));
        assertThat(caught).isInstanceOf(MalformedPayloadException.class);
        assertThat(caught).hasMessage("error.request.field-type-mismatch: [/, NameFieldType, Integer]");
    }

}
