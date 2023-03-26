package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static ltd.highsoft.frameworks.domain.core.Name.name;
import static org.assertj.core.api.Assertions.*;

class NameFieldTypeTest {

    @Test
    void should_be_able_to_construct_names_from_strings() {
        assertThat(NameFieldType.asName().from("John", "")).isEqualTo(name("John"));
    }

    @Test
    void should_reject_type_mismatched_inputs() {
        Throwable caught = catchThrowable(() -> NameFieldType.asName().from(1, "/"));
        assertThat(caught).isInstanceOf(MalformedPayloadException.class);
        assertThat(caught).hasMessage("error.request.field-type-mismatch: [/, NameFieldType, Integer]");
    }

}
