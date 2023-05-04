package ltd.highsoft.hare.frameworks.test.web;

import org.junit.jupiter.api.Test;

import java.nio.file.*;
import java.util.Map;

import static ltd.highsoft.hare.frameworks.test.web.ApiDocUtils.*;
import static ltd.highsoft.hare.frameworks.test.web.ConstrainedFields.constrainedFieldWithPath;
import static ltd.highsoft.hare.frameworks.test.web.ConstrainedParameters.parameterWithConstraints;
import static ltd.highsoft.hare.frameworks.test.web.Documentation.doc;
import static ltd.highsoft.hare.frameworks.test.web.PathVariables.variables;
import static ltd.highsoft.hare.frameworks.test.web.RequestParameters.parameters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class DocumentationTest extends IntegrationTest {

    @Test
    @WithGrantedAuthorities({})
    void should_generate_document_header() {
        var response = get("/web-test/api-header/{id}", variables(Map.of("id", "5")), doc("api-header"));
        response.statusCode(is(200));
        assertThat(Files.exists(Path.of("build/generated-snippets/api-header/api-header.adoc"))).isTrue();
        assertThat(Files.exists(Path.of("build/generated-snippets/api-header/api.adoc"))).isTrue();
    }

    @Test
    void should_generate_document_header_without_path_variables() {
        var response = get("/web-test/api-header", doc("api-header-without-path-parameters"));
        response.statusCode(is(200));
        assertThat(Files.exists(Path.of("build/generated-snippets/api-header-without-path-parameters/api-header.adoc"))).isTrue();
    }

    @Test
    void should_document_path_variables_and_constrained_fields() {
        var response = post("/web-test/document-constrained-fields/{id}", variables(Map.of("id", "5")), Map.of("name", "John"), doc("constrained-fields",
            requestFields(
                constrainedFieldWithPath("name", "Can NOT be empty").description("Name for new user")
            ))
        );
        response.statusCode(is(200));
        assertThat(Files.exists(Path.of("build/generated-snippets/constrained-fields/request-fields.adoc"))).isTrue();
    }

    @Test
    void should_document_constrained_parameters() {
        var parameters = parameters(Map.of("q", "john", "size", 10, "page", 0));
        var response = get("/web-test/document-constrained-parameters", parameters, doc("constrained-parameters",
            pagedRequestParameters(
                parameterWithConstraints("q", "String", "Can NOT be empty").description("Keyword for searching")
            ))
        );
        response.statusCode(is(200));
        assertThat(Files.exists(Path.of("build/generated-snippets/constrained-parameters/query-parameters.adoc"))).isTrue();
    }

    @Test
    void should_document_paged_responses() {
        var response = get("/web-test/document-paged-response", parameters(Map.of()), doc("paged-response",
            ApiDocUtils.pagedResponseFields(
                fieldWithPath("content[].name").description("User name")
            ))
        );
        response.statusCode(is(200));
        assertThat(Files.exists(Path.of("build/generated-snippets/paged-response/response-fields.adoc"))).isTrue();
    }

}
