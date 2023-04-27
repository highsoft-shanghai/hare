package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CodeFieldTypeTest {

    @Test
    void should_be_able_to_construct_codes_from_strings() {
        assertThat(CodeFieldType.asCode().from("0001", "")).isEqualTo(Code.code("0001"));
    }

    @Test
    void should_reject_type_mismatched_inputs() {
        Throwable caught = catchThrowable(() -> CodeFieldType.asCode().from(1, "/"));
        assertThat(caught).isInstanceOf(MalformedPayloadException.class);
        assertThat(caught).hasMessage("error.request.field-type-mismatch: [/, CodeFieldType, Integer]");
    }

}
