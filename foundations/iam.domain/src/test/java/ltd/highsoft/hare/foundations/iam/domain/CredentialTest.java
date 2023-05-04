package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.FixedIdGenerator;
import ltd.highsoft.hare.frameworks.domain.core.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class CredentialTest {

    private @Mock PasswordEncoder passwordEncoder;
    private Credential credential;

    @BeforeEach
    void setUp() {
        DefaultCredentialFactory factory = new DefaultCredentialFactory(new FixedIdGenerator(id("next-id")));
        credential = factory.newCredential(new CredentialOwner(id("john@highsoft.ltd"), id("john"), id("highsoft")), CredentialType.USERNAME_AND_PASSWORD, LoginName.loginName("john"), EncryptedSecret.encryptedSecret("encrypted-secret"));
    }

    @Test
    void should_match_correct_secret() {
        given(passwordEncoder.matches("correct-plan-secret", "encrypted-secret")).willReturn(true);
        assertThat(credential.matchSecret(PlanSecret.from("correct-plan-secret"), passwordEncoder)).usingRecursiveComparison().isEqualTo(MatchResult.success(credential));
    }

    @Test
    void should_mismatch_incorrect_secret() {
        given(passwordEncoder.matches("incorrect-plan-secret", "encrypted-secret")).willReturn(false);
        assertThat(credential.matchSecret(PlanSecret.from("incorrect-plan-secret"), passwordEncoder)).usingRecursiveComparison().isEqualTo(MatchResult.fail("iam.username-or-password-wrong"));
    }

}
