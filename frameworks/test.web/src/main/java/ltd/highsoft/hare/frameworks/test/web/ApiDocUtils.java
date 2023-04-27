package ltd.highsoft.hare.frameworks.test.web;

import lombok.*;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.restdocs.payload.*;
import org.springframework.restdocs.request.*;

import static ltd.highsoft.hare.frameworks.test.web.ConstrainedParameters.parameterWithConstraints;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiDocUtils {

    public static ApiHeaderSnippet apiHeader(boolean authorizationRequired) {
        return new ApiHeaderSnippet(authorizationRequired);
    }

    public static QueryParametersSnippet pagedRequestParameters(ParameterDescriptor... descriptors) {
        ParameterDescriptor[] paginationParameters = {
            parameterWithConstraints("size", "Number", "必须大于等于1且小于等于999999").description("分页大小"),
            parameterWithConstraints("page", "Number", "必须大于等于0且小于等于99999999").description("以0为首页的页码")
        };
        return RequestDocumentation.queryParameters(ArrayUtils.addAll(descriptors, paginationParameters));
    }

    public static ResponseFieldsSnippet pagedResponseFields(FieldDescriptor... descriptors) {
        FieldDescriptor[] paginationFields = {
            fieldWithPath("first").description("是否首页。true表示当前页为首页，false则为非首页"),
            fieldWithPath("last").description("是否末页。true表示当前页为末页，false则为非末页"),
            fieldWithPath("numberOfTotalPages").description("总页数"),
            fieldWithPath("numberOfTotalElements").description("总记录数"),
            fieldWithPath("numberOfElements").description("当前页内记录数"),
            fieldWithPath("size").description("分页大小"),
            fieldWithPath("number").description("页码"),
            fieldWithPath("sort").optional().description("排序信息"),
            fieldWithPath("sort[].property").type("String").description("排序字段"),
            fieldWithPath("sort[].direction").type("String").description("排序方向。asc为升序，desc为降序")
        };
        return responseFields(ArrayUtils.addAll(descriptors, paginationFields));
    }

}
