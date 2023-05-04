package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.security.core.ContextProvider;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@MockitoSettings
public class AccessTokenContextProviderTest {

    private @Mock AccessTokens accessTokens;

    @BeforeEach
    void setUp() {
        given(accessTokens.getOptional(id("token-id"))).willReturn(Optional.of(TestAccessTokens.ACCESS_TOKEN_OF_TESTER));
    }

    @Test
    void should_load_accesses_token_from_underling_repository() {
        ContextProvider provider = new AccessTokenContextProvider(accessTokens);
        assertThat(provider.get(id("token-id"))).isEqualTo(Optional.of(TestAccessTokens.ACCESS_TOKEN_OF_TESTER));
    }

}

