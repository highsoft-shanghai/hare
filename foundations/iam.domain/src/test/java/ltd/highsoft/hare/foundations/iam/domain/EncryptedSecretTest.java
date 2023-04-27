package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.PasswordEncoder;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@MockitoSettings(strictness = Strictness.LENIENT)
class EncryptedSecretTest {

    private @Mock PasswordEncoder passwordEncryptor;

    @BeforeEach
    void setUp() {
        given(passwordEncryptor.matches("correct-plan-secret", "encrypted-secret")).willReturn(true);
        given(passwordEncryptor.matches("incorrect-plan-secret", "encrypted-secret")).willReturn(false);
    }

    @Test
    void should_be_able_to_match_correct_plan_secrets() {
        assertThat(EncryptedSecret.encryptedSecret("encrypted-secret").match(PlanSecret.from("correct-plan-secret"), passwordEncryptor)).isTrue();
    }

    @Test
    void should_be_able_to_match_incorrect_plan_secrets() {
        assertThat(EncryptedSecret.encryptedSecret("encrypted-secret").match(PlanSecret.from("incorrect-plan-secret"), passwordEncryptor)).isFalse();
    }

}
