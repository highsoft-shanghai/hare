package ltd.highsoft.hare.foundations.iam.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlanSecretTest {

    @Test
    void should_hold_plan_values() {
        PlanSecret secret = PlanSecret.from("123456");
        assertThat(secret.asString()).isEqualTo("123456");
    }

}
