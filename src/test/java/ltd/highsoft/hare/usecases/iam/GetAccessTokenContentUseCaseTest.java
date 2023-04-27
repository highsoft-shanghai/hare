package ltd.highsoft.hare.usecases.iam;

import ltd.highsoft.hare.frameworks.test.web.Documentation;
import ltd.highsoft.hare.frameworks.test.web.GlobalTestContext;
import ltd.highsoft.hare.frameworks.test.web.WithGrantedAuthorities;
import ltd.highsoft.hare.ApiTest;
import org.junit.jupiter.api.Test;

import static ltd.highsoft.hare.frameworks.test.web.Documentation.doc;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class GetAccessTokenContentUseCaseTest extends ApiTest {

    @Test
    @WithGrantedAuthorities({"feature-1", "feature-2"})
    void should_be_able_to_get_content_of_current_access_token() {
        var response = get("/access-tokens/current", document());
        response.statusCode(is(200)).body("accessToken", is("tester-token-id")).body("authorities", hasItems("feature-1", "feature-2"));
        response.body("userAccountName", is(GlobalTestContext.userAccount().name()));
        response.body("tenantName", is(GlobalTestContext.tenant().name()));
    }

    @Override
    protected Documentation document() {
        return doc("access-tokens.current.get",
            responseFields(
                fieldWithPath("accessToken").description("访问令牌标识"),
                fieldWithPath("authorities").description("授予的权限集合"),
                fieldWithPath("userAccountName").description("当前登录用户的名称"),
                fieldWithPath("tenantName").description("当前登录用户所在企业的名称")
            )
        );
    }

}
