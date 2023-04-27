package ltd.highsoft.hare.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class DecimalFieldTypeTest {

    @Test
    void should_be_able_to_convert_into_decimals_from_number_values() {
        assertThat(DecimalFieldType.asDecimal().from(13.5, "")).isEqualTo(new BigDecimal("13.5"));
    }

    @Test
    void should_be_able_to_convert_into_decimals_from_string_values() {
        assertThat(DecimalFieldType.asDecimal().from("13.5", "")).isEqualTo(new BigDecimal("13.5"));
    }

    @Test
    void should_reject_values_with_wrong_type() {
        assertThatThrownBy(() -> DecimalFieldType.asDecimal().from(true, "")).isInstanceOf(MalformedPayloadException.class);
    }

}
