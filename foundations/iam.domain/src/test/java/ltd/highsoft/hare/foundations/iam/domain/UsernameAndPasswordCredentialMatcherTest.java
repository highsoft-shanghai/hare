package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Map;
import java.util.Optional;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.frameworks.domain.core.Payload.payload;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.quality.Strictness.LENIENT;

@MockitoSettings(strictness = LENIENT)
public class UsernameAndPasswordCredentialMatcherTest {

    public static final CredentialOwner CREDENTIAL_OWNER = new CredentialOwner(id("account-id"), id("u-id"), id("t-id"));
    private static final Credential CORRECT = new Credential(id("correct-id"), CREDENTIAL_OWNER, CredentialType.USERNAME_AND_PASSWORD, LoginName.loginName("correct"), EncryptedSecret.encryptedSecret("encrypted"), false);
    private static final Credential INCORRECT = new Credential(id("incorrect-id"), CREDENTIAL_OWNER, CredentialType.USERNAME_AND_PASSWORD, LoginName.loginName("incorrect"), EncryptedSecret.encryptedSecret("unknown"), false);
    private @Mock PasswordEncoder passwordEncoder;
    private @Mock Credentials credentials;
    private CredentialMatcher matcher;

    @BeforeEach
    void setUp() {
        this.matcher = new UsernameAndPasswordCredentialMatcher(credentials, passwordEncoder);
        given(passwordEncoder.matches("correct", "encrypted")).willReturn(true);
        given(passwordEncoder.matches("incorrect", "encrypted")).willReturn(false);
        given(credentials.credentialFor(CredentialType.USERNAME_AND_PASSWORD, "correct")).willReturn(Optional.of(CORRECT));
        given(credentials.credentialFor(CredentialType.USERNAME_AND_PASSWORD, "incorrect")).willReturn(Optional.of(INCORRECT));
    }

    @Test
    void should_get_success_when_matching_correct_username_and_password() {
        var matchResult = matcher.match(payload(Map.of("username", "correct", "password", "correct")));
        assertThat(matchResult.matched()).isTrue();
        assertThat(matchResult.reason()).isEmpty();
        assertThat(matchResult.credential()).isEqualTo(CORRECT);
    }

    @Test
    void should_get_failed_when_matching_incorrect_username_or_password() {
        var matchResult = matcher.match(payload(Map.of("username", "incorrect", "password", "correct")));
        assertThat(matchResult.matched()).isFalse();
        assertThat(matchResult.reason()).isEqualTo("iam.password-not-right");
        assertThat(matchResult.credential()).isNull();
    }

    @Test
    void should_get_failed_when_matching_non_existing_credentials() {
        var matchResult = matcher.match(payload(Map.of("username", "non-existing", "password", "correct")));
        assertThat(matchResult.matched()).isFalse();
        assertThat(matchResult.reason()).isEqualTo("iam.user-does-not-exist");
        assertThat(matchResult.credential()).isNull();
    }

}
