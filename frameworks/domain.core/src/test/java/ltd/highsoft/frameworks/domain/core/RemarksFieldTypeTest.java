package ltd.highsoft.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static ltd.highsoft.frameworks.domain.core.Remarks.remarks;
import static org.assertj.core.api.Assertions.*;

class RemarksFieldTypeTest {

    @Test
    void should_be_able_to_construct_remarks_from_strings() {
        assertThat(RemarksFieldType.asRemarks().from("Pretty good", "")).isEqualTo(remarks("Pretty good"));
    }

    @Test
    void should_reject_type_mismatched_inputs() {
        Throwable caught = catchThrowable(() -> RemarksFieldType.asRemarks().from(1, "/"));
        assertThat(caught).isInstanceOf(MalformedPayloadException.class);
        assertThat(caught).hasMessage("error.request.field-type-mismatch: [/, RemarksFieldType, Integer]");
    }

}
