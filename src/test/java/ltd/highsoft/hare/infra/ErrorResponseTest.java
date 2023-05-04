package ltd.highsoft.hare.infra;

import ltd.highsoft.hare.ApiTest;
import ltd.highsoft.hare.frameworks.test.web.Documentation;
import ltd.highsoft.hare.frameworks.test.web.WithGrantedAuthorities;
import org.junit.jupiter.api.Test;

import static ltd.highsoft.hare.frameworks.test.web.Documentation.doc;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class ErrorResponseTest extends ApiTest {

    @Test
    @WithGrantedAuthorities("f1")
    void should_report_error_info_correctly() {
        clearAccessTokenFromDatabase();
        var response = get("/access-tokens/current", document());
        response.statusCode(is(401)).body("message", either(is("无效的token")).or(is("Bad token")));
    }

    @Override
    protected Documentation document() {
        return doc("error.general-error",
            responseFields(
                fieldWithPath("timestamp").description("Error happened time"),
                fieldWithPath("status").description("HTTP status code"),
                fieldWithPath("error").description("HTTP status description"),
                fieldWithPath("message").description("Localization error description"),
                fieldWithPath("path").description("Resource path")
            )
        );
    }

}
