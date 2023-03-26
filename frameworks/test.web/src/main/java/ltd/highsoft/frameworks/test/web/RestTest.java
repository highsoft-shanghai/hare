package ltd.highsoft.frameworks.test.web;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import ltd.highsoft.frameworks.domain.core.Id;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.restdocs.restassured.RestAssuredOperationPreprocessorsConfigurer;

import javax.annotation.Nullable;
import java.io.File;
import java.lang.reflect.Method;

import static ltd.highsoft.frameworks.security.core.SecurityContext.ANONYMOUS;
import static ltd.highsoft.frameworks.test.web.ApiDocUtils.apiHeader;
import static ltd.highsoft.frameworks.test.web.PathVariables.variables;
import static ltd.highsoft.frameworks.test.web.RequestParameters.parameters;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

@AutoConfigureRestDocs
public class RestTest {

    private final ManualRestDocumentation documentation = new ManualRestDocumentation();
    private RequestSpecification spec;
    private @LocalServerPort int port;

    protected ValidatableResponse get(String path) {
        return given(null).when().get(path).then();
    }

    protected ValidatableResponse get(String path, Documentation doc) {
        return get(path, parameters(), doc);
    }

    protected ValidatableResponse get(String path, RequestParameters parameters) {
        return get(path, parameters, null);
    }

    protected ValidatableResponse get(String path, RequestParameters parameters, @Nullable Documentation doc) {
        return given(doc).params(parameters.asMap()).when().get(path).then();
    }

    protected ValidatableResponse get(String path, PathVariables variables, @Nullable Documentation doc) {
        return given(doc).pathParams(variables.asMap()).when().get(path).then();
    }

    protected ValidatableResponse post(String path, Object body) {
        return post(path, body, null);
    }

    protected ValidatableResponse post(String path, Object body, @Nullable Documentation doc) {
        return post(path, variables(), body, doc);
    }

    protected ValidatableResponse post(String path, PathVariables variables, Object body) {
        return post(path, variables, body, null);
    }

    protected ValidatableResponse post(String path, PathVariables variables, Object body, @Nullable Documentation doc) {
        return given(doc).pathParams(variables.asMap()).body(body).contentType(ContentType.JSON).when().post(path).then();
    }

    protected ValidatableResponse post(String path, PathVariables variables, File file, @Nullable Documentation doc) {
        return given(doc).pathParams(variables.asMap()).header("Content-Type", "multipart/form-data").multiPart("file", file).when().post(path).then();
    }

    protected ValidatableResponse put(String path, Object body) {
        return put(path, body, null);
    }

    protected ValidatableResponse put(String path, Object body, @Nullable Documentation doc) {
        return put(path, variables(), body, doc);
    }

    protected ValidatableResponse put(String path, PathVariables variables, Object body, @Nullable Documentation doc) {
        return given(doc).pathParams(variables.asMap()).body(body).contentType(ContentType.JSON).when().put(path).then();
    }

    protected ValidatableResponse delete(String path, PathVariables variables) {
        return delete(path, variables, null);
    }

    protected ValidatableResponse delete(String path, PathVariables variables, @Nullable Documentation doc) {
        return given(doc).pathParams(variables.asMap()).when().delete(path).then();
    }

    protected RequestSpecification given(@Nullable Documentation doc) {
        return givenAuth(RestAssured.given(spec)).port(port).accept(ContentType.JSON).contentType(ContentType.JSON).filter(docFilter(doc));
    }

    protected int port() {
        return port;
    }

    @BeforeEach
    void setupRestDoc(TestInfo info) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        GlobalTestContext.token().ifPresent(x -> setupAuth(builder, x));
        this.spec = builder.addFilter(documentationFilter()).build();
        this.documentation.beforeTest(getClass(), info.getTestMethod().map(Method::getName).orElse(""));
    }

    @AfterEach
    void tearDownRestDoc() {
        this.documentation.afterTest();
    }

    private RequestSpecification givenAuth(RequestSpecification requestSpecification) {
        return GlobalTestContext.token().map(x -> requestSpecification.auth().oauth2(x.asString())).orElse(requestSpecification);
    }

    private void setupAuth(RequestSpecBuilder builder, Id token) {
        var scheme = new PreemptiveOAuth2HeaderScheme();
        scheme.setAccessToken(token.asString());
        builder.setAuth(scheme);
    }

    private Filter docFilter(Documentation doc) {
        if (doc == null) return EmptyFilter.INSTANCE;
        var authRequired = GlobalTestContext.token().stream().anyMatch(x -> !x.equals(ANONYMOUS.token()));
        return document(doc.identifier(), ArrayUtils.addAll(doc.snippets(), apiHeader(authRequired), new ApiSnippet()));
    }

    private RestAssuredOperationPreprocessorsConfigurer documentationFilter() {
        return documentationConfiguration(this.documentation).operationPreprocessors().withRequestDefaults(prettyPrint()).withResponseDefaults(prettyPrint());
    }

}
