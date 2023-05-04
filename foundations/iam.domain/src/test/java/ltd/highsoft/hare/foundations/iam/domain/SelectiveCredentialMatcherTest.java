package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.PasswordEncoder;
import ltd.highsoft.hare.frameworks.domain.core.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.Payload.payload;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class SelectiveCredentialMatcherTest {

    private Credentials credentials;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        credentials = mock(Credentials.class);
        passwordEncoder = mock(PasswordEncoder.class);
    }

    @Test
    void should_return_failed_result_when_no_such_login_type() {
        final SelectiveCredentialMatcher selectiveCredentialMatcher = new SelectiveCredentialMatcher(credentials, passwordEncoder);
        Payload payload = payload(Map.of("type", "error-login-type"));
        MatchResult matchResult = selectiveCredentialMatcher.match(payload);
        assertThat(matchResult.matched()).isFalse();
        assertThat(matchResult.reason()).isEqualTo("iam.no-matched-login-type");
    }

    @Test
    void should_return_failed_result_when_login_type_missing() {
        final SelectiveCredentialMatcher selectiveCredentialMatcher = new SelectiveCredentialMatcher(credentials, passwordEncoder);
        Payload payload = payload(Map.of());
        MatchResult matchResult = selectiveCredentialMatcher.match(payload);
        assertThat(matchResult.matched()).isFalse();
        assertThat(matchResult.reason()).isEqualTo("iam.missing-login-type");
    }

}
