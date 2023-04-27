package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FieldFieldTypeTest {

    @Test
    void should_be_able_to_convert_into_nulls_from_null_values() {
        assertThat(StringFieldType.asString().allowNull().from(null, "")).isEqualTo(null);
    }

    @Test
    void should_reject_type_mismatched_inputs() {
        Throwable caught = catchThrowable(() -> StringFieldType.asString().from(1, "/"));
        assertThat(caught).isInstanceOf(MalformedPayloadException.class);
        assertThat(caught).hasMessage("error.request.field-type-mismatch: [/, StringFieldType, Integer]");
    }

    @Test
    void should_reject_null_inputs() {
        Throwable caught = catchThrowable(() -> StringFieldType.asString().from(null, "/"));
        assertThat(caught).isInstanceOf(MalformedPayloadException.class);
        assertThat(caught).hasMessage("error.request.missing-field: [/, StringFieldType]");
    }

}
