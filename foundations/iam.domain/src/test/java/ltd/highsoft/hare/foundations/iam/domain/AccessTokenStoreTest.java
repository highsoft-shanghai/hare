package ltd.highsoft.hare.foundations.iam.domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static ltd.highsoft.hare.foundations.iam.domain.TestAccessTokens.ACCESS_TOKEN_OF_TESTER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@MockitoSettings
class AccessTokenStoreTest {

    private @Mock AccessTokens accessTokens;

    @Test
    void should_store_access_tokens_into_underlying_collection_when_client_stores_access_tokens() {
        AccessTokenStore store = new AccessTokenStore(accessTokens);
        store.store(ACCESS_TOKEN_OF_TESTER);
        then(accessTokens).should().get(ACCESS_TOKEN_OF_TESTER);
    }

    @Test
    void should_load_access_tokens_from_underlying_collection_when_client_loads_access_tokens() {
        AccessTokenStore store = new AccessTokenStore(accessTokens);
        given(accessTokens.getOptional(ACCESS_TOKEN_OF_TESTER.token())).willReturn(Optional.of(ACCESS_TOKEN_OF_TESTER));
        assertThat(store.load(ACCESS_TOKEN_OF_TESTER.token())).hasValue(ACCESS_TOKEN_OF_TESTER);
    }

    @Test
    void should_only_keep_one_access_token_in_the_store_for_single_user_account_and_group() {
        AccessTokenStore store = new AccessTokenStore(accessTokens);
        store.store(ACCESS_TOKEN_OF_TESTER);
        then(accessTokens).should().removeAll(ACCESS_TOKEN_OF_TESTER.owner().userAccount().id(), ACCESS_TOKEN_OF_TESTER.group());
    }

}
