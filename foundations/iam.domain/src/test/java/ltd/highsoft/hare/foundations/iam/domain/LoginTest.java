package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Identity;
import ltd.highsoft.hare.frameworks.domain.core.Payload;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.I18nMessage.message;
import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;
import static ltd.highsoft.hare.frameworks.domain.core.Payload.payload;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@MockitoSettings
public class LoginTest {

    private static final CredentialOwner CREDENTIAL_OWNER = new CredentialOwner(id("john@highsoft.ltd"), id("john"), id("highsoft"));
    private final Payload payload = payload(Map.of("type", "username-and-password", "username", "john", "password", "123456", "group", "web"));
    private @Mock CredentialMatcher credentialMatcher;
    private @Mock AccessTokenStore accessTokenStore;
    private @Mock AccessTokenFactory accessTokenFactory;
    private AccessToken issuedAccessToken;
    private Credential credential;
    private Login login;

    @BeforeEach
    void setup() {
        Identity userAccount = identity("john@highsoft.ltd", "John@Highsoft");
        Identity user = identity("john", "John");
        Identity tenant = identity("highsoft", "Highsoft");
        issuedAccessToken = new AccessToken(id("token-1"), new AccessTokenOwner(userAccount, user, tenant), GrantedAuthorities.of("f1", "f2"), "web");
        credential = new Credential(id("credential-id"), CREDENTIAL_OWNER, CredentialType.USERNAME_AND_PASSWORD, LoginName.loginName("john"), EncryptedSecret.encryptedSecret("123455"), false);
        login = new Login(id("login-1"), credentialMatcher, accessTokenFactory, accessTokenStore);
    }

    @Test
    void should_provide_usable_info_when_login_approved() {
        given(credentialMatcher.match(payload)).willReturn(MatchResult.success(credential));
        given(accessTokenFactory.newAccessToken(CREDENTIAL_OWNER, "web")).willReturn(issuedAccessToken);
        LoginResult result = login.submit(payload);
        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getId()).isEqualTo(login.id().asString());
        assertThat(result.getAccessToken()).isEqualTo("token-1");
    }

    @Test
    void should_provide_usable_info_when_login_rejected() {
        given(credentialMatcher.match(any())).willReturn(MatchResult.fail("failure reason"));
        LoginResult loginResult = login.submit(payload);
        assertThat(loginResult.isSuccess()).isFalse();
        assertThat(loginResult.getAccessToken()).isEmpty();
        assertThat(loginResult.getId()).isEqualTo(login.id().asString());
        assertThat(loginResult.getReason()).isEqualTo(message("iam.username-or-password-wrong"));
    }

    @Test
    void should_be_able_to_issue_access_tokens_when_login_approved() {
        given(credentialMatcher.match(any())).willReturn(MatchResult.success(credential));
        given(accessTokenFactory.newAccessToken(CREDENTIAL_OWNER, "web")).willReturn(issuedAccessToken);
        LoginResult loginResult = login.submit(payload);
        assertThat(loginResult.isSuccess()).isTrue();
        then(accessTokenStore).should().store(issuedAccessToken);
    }

}
