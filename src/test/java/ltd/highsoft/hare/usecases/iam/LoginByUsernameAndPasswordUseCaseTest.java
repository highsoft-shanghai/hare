package ltd.highsoft.hare.usecases.iam;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.ApiTest;
import ltd.highsoft.hare.foundations.iam.domain.*;
import ltd.highsoft.hare.frameworks.domain.core.GlobalIdGeneratorResetter;
import ltd.highsoft.hare.frameworks.domain.core.Remarks;
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

import static ltd.highsoft.hare.foundations.iam.domain.CredentialType.USERNAME_AND_PASSWORD;
import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;
import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static ltd.highsoft.hare.frameworks.test.web.ConstrainedFields.constrainedFieldWithPath;
import static ltd.highsoft.hare.frameworks.test.web.Documentation.doc;
import static ltd.highsoft.hare.frameworks.test.web.PathVariables.variables;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class LoginByUsernameAndPasswordUseCaseTest extends ApiTest {

    private static final User USER = User.restore("john", "John", false);
    private static final Tenant TENANT = Tenant.restore("highsoft", "Highsoft");
    private static final LoginName LOGIN_NAME = LoginName.loginName("john");
    private static final String PLAN_SECRET = "s2324sdfsfdfgdfgfhfhg";
    private static final EncryptedSecret ENCRYPTED_SECRET = EncryptedSecret.encryptedSecret("$2a$10$HNw/E.gW202tMYSt11lNBeL2g13KQxJRt7QWzOdqEKxiUN2lWrQse");
    private static final Role OPERATORS = new Role(ScopedId.id("operators", "highsoft"), name("Operators"), GrantedAuthorities.of("f1", "f2"), Remarks.remarks("Remarks for operators"), false);
    private static final Role OPERATORS_2 = new Role(ScopedId.id("operators2", "highsoft"), name("Operators"), GrantedAuthorities.of("f3"), Remarks.remarks("Remarks for operators"), false);
    private @Resource UserAccounts userAccounts;
    private @Resource Tenants tenants;
    private @Resource Credentials credentials;
    private @Resource Roles roles;
    private @Resource AccessTokens accessTokens;
    private @Resource Users users;
    private UserAccount userAccount;
    private Credential credential;

    @BeforeEach
    public void setUp() {
        this.userAccount = new UserAccount(id("john@highsoft.ltd"), "John@Highsoft", new UserAccountOwner(USER.id(), TENANT.id()), new UserAccountRoles(Set.of("operators", "operators2"), roles), false);
        credential = new Credential(id("CREDENTIAL_ID"), new CredentialOwner(userAccount.id(), USER.id(), TENANT.id()), USERNAME_AND_PASSWORD, LOGIN_NAME, ENCRYPTED_SECRET, false);
        tenants.add(TENANT);
        users.add(USER);
        userAccounts.add(userAccount);
        credentials.add(credential);
        roles.add(OPERATORS);
        roles.add(OPERATORS_2);
    }

    @Test
    public void should_be_able_to_successfully_login() {
        GlobalIdGeneratorResetter.reset(new UuidBasedIdGenerator());
        var response = post("/logins", variables(), Map.of("type", "username-and-password", "username", LOGIN_NAME.asString(), "password", PLAN_SECRET, "group", "web"), document());
        response.statusCode(is(201));
        response.body("accessToken", is(not(empty())));
        Optional<AccessToken> accessToken = accessTokens.getOptional(id(response.extract().body().jsonPath().getString("accessToken")));
        assertThat(accessToken.map(AccessToken::owner)).hasValue(new AccessTokenOwner(userAccount.asIdentity(), identity("", ""), TENANT.asIdentity()));
        assertThat(accessToken.map(AccessToken::grantedAuthorities)).hasValue(GrantedAuthorities.of("f1", "f2", "f3", "operators", "operators2"));
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
        return doc("logins.username-and-password.post",
                requestFields(
                        constrainedFieldWithPath("type", "用户名密码请使用“username-and-password”").description("登录凭据类型"),
                        constrainedFieldWithPath("username", "长度≧3且≦200").description("登录用户名"),
                        constrainedFieldWithPath("password", "长度≧6且≦100").description("登录密码，建议使用SHA512取摘要后的密码"),
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
