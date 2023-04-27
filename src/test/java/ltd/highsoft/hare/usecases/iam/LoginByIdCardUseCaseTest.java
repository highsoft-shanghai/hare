package ltd.highsoft.hare.usecases.iam;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.ApiTest;
import ltd.highsoft.hare.foundations.iam.domain.*;
import ltd.highsoft.hare.frameworks.domain.core.GlobalIdGeneratorResetter;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;
import ltd.highsoft.hare.frameworks.domain.core.UuidBasedIdGenerator;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import ltd.highsoft.hare.frameworks.test.web.Documentation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static ltd.highsoft.hare.foundations.iam.domain.CredentialType.CARD;
import static ltd.highsoft.hare.foundations.iam.domain.EncryptedSecret.encryptedSecret;
import static ltd.highsoft.hare.foundations.iam.domain.LoginName.loginName;
import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;
import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static ltd.highsoft.hare.frameworks.domain.core.Remarks.remarks;
import static ltd.highsoft.hare.frameworks.test.web.ConstrainedFields.constrainedFieldWithPath;
import static ltd.highsoft.hare.frameworks.test.web.Documentation.doc;
import static ltd.highsoft.hare.frameworks.test.web.PathVariables.variables;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class LoginByIdCardUseCaseTest extends ApiTest {

    private static final User USER = User.restore("john", "John", false);
    private static final Tenant TENANT = Tenant.restore("highsoft", "Highsoft");
    private static final LoginName LOGIN_NAME = loginName("123456789");
    private static final Role OPERATORS = new Role(ScopedId.id("operators", "highsoft"), name("Operators"), GrantedAuthorities.of("f1", "f2"), remarks("Remarks for operators"), false);
    private @Resource UserAccounts userAccounts;
    private @Resource Tenants tenants;
    private @Resource Credentials credentials;
    private @Resource Roles roles;
    private @Resource AccessTokens accessTokens;
    private @Resource Users users;
    private UserAccount userAccount;
    private Credential credential;
    private @Resource Logins logins;

    @BeforeEach
    public void setUp() {
        this.userAccount = new UserAccount(id("john@highsoft.ltd"), "John@Highsoft", new UserAccountOwner(USER.id(), TENANT.id()), new UserAccountRoles(Set.of("operators"), roles), false);
        credential = new Credential(id("CREDENTIAL_ID"), new CredentialOwner(userAccount.id(), USER.id(), TENANT.id()), CARD, LOGIN_NAME, encryptedSecret(""), false);
        tenants.add(TENANT);
        users.add(USER);
        userAccounts.add(userAccount);
        credentials.add(credential);
        roles.add(OPERATORS);
    }

    @Test
    public void should_be_able_to_successfully_login() {
        GlobalIdGeneratorResetter.reset(new UuidBasedIdGenerator());
        var response = post("/logins", variables(), Map.of("type", "card", "cardNumber", LOGIN_NAME.asString(), "group", "web"), document());
        response.statusCode(is(201));
        response.body("accessToken", is(not(empty())));
        Optional<AccessToken> accessToken = accessTokens.getOptional(id(response.extract().body().jsonPath().getString("accessToken")));
        assertThat(accessToken.map(AccessToken::owner)).hasValue(new AccessTokenOwner(userAccount.asIdentity(), identity("", ""), TENANT.asIdentity()));
        assertThat(accessToken.map(AccessToken::grantedAuthorities)).hasValue(GrantedAuthorities.of("f1", "f2", "operators"));
    }

    @AfterEach
    public void tearDown() {
        tenants.remove(TENANT.id());
        users.remove(USER.id());
        userAccounts.remove(userAccount.id());
        credentials.remove(credential.id());
        accessTokens.removeAll(userAccount.id(), "web");
        roles.remove(ScopedId.id(OPERATORS.id().id(), id("highsoft")));
    }

    @Override
    protected Documentation document() {
        return doc("logins.card.post",
            requestFields(
                constrainedFieldWithPath("type", "ID卡请使用“card”").description("登录凭据类型"),
                constrainedFieldWithPath("cardNumber", "长度≧3且≦2000").description("登录用户名"),
                constrainedFieldWithPath("group", "不可为空，长度≦200").description("令牌组，用来表示登录的端")
            ),
            responseFields(
                fieldWithPath("id").description("登录记录ID"),
                fieldWithPath("success").description("登录成功状态"),
                fieldWithPath("accessToken").description("访问令牌标识"),
                fieldWithPath("reason").description("如果登录失败，显示失败的原因")
            )
        );
    }

}
