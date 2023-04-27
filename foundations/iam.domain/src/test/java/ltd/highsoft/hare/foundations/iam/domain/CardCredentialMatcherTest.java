package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Payload;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.*;

import static ltd.highsoft.hare.foundations.iam.domain.CredentialType.CARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class CardCredentialMatcherTest {

    private @Mock Credential credential;
    private @Mock Credentials credentials;

    @Test
    void should_be_matched_when_match_existing_card_numbers() {
        given(credentials.credentialFor(CARD, "1234567890")).willReturn(Optional.of(credential));
        CardCredentialMatcher matcher = new CardCredentialMatcher(credentials);
        MatchResult result = matcher.match(Payload.payload(Map.of("type", CARD.asString(), "cardNumber", "1234567890", "group", "web")));
        assertThat(result.matched()).isTrue();
        assertThat(result.credential()).isEqualTo(credential);
    }

    @Test
    void should_be_mismatched_when_match_non_existing_card_numbers() {
        CardCredentialMatcher matcher = new CardCredentialMatcher(credentials);
        MatchResult result = matcher.match(Payload.payload(Map.of("type", CARD.asString(), "cardNumber", "1234567890", "group", "web")));
        assertThat(result.matched()).isFalse();
        assertThat(result.reason()).isEqualTo("iam.user-does-not-exist");
    }

}
