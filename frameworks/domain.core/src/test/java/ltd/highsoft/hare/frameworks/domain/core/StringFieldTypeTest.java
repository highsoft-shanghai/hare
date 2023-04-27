package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StringFieldTypeTest {

    @Test
    void should_be_able_to_convert_into_strings_from_null_values() {
        assertThat(StringFieldType.asString().nullToEmpty().from(null, "")).isEqualTo("");
    }

    @Test
    void should_reject_type_mismatched_inputs() {
        Throwable caught = catchThrowable(() -> StringFieldType.asString().from(1, "/"));
        assertThat(caught).isInstanceOf(MalformedPayloadException.class);
        assertThat(caught).hasMessage("error.request.field-type-mismatch: [/, StringFieldType, Integer]");
    }

}
