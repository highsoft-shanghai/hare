package ltd.highsoft.hare.infra;

import ltd.highsoft.hare.ApiTest;
import ltd.highsoft.hare.frameworks.test.web.Documentation;
import ltd.highsoft.hare.frameworks.test.web.WithGrantedAuthorities;
import org.junit.jupiter.api.Test;

import static ltd.highsoft.hare.frameworks.test.web.Documentation.doc;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class ErrorResponseTest extends ApiTest {

    @Test
    @WithGrantedAuthorities("f1")
    void should_report_error_info_correctly() {
        clearAccessTokenFromDatabase();
        var response = get("/access-tokens/current", document());
        response.statusCode(is(401)).body("message", is("error.bad-token"));
    }

    @Override
    protected Documentation document() {
        return doc("error.general-error",
            responseFields(
                fieldWithPath("timestamp").description("错误发生时间"),
                fieldWithPath("status").description("HTTP状态码"),
                fieldWithPath("error").description("HTTP状态描述"),
                fieldWithPath("message").description("本地化错误描述"),
                fieldWithPath("path").description("资源路程")
            )
        );
    }

}
