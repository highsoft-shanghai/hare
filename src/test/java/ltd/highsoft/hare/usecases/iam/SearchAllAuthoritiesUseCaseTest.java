package ltd.highsoft.hare.usecases.iam;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.test.web.Documentation;
import ltd.highsoft.hare.frameworks.test.web.WithGrantedAuthorities;
import ltd.highsoft.hare.ApiTest;
import ltd.highsoft.hare.foundations.iam.domain.Authorities;
import ltd.highsoft.hare.foundations.iam.domain.Authority;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class SearchAllAuthoritiesUseCaseTest extends ApiTest {

    private @Resource Authorities authorities;

    @BeforeEach
    public void setUp() {
        authorities.clear();
        authorities.get(new Authority(Id.id("UseCase-1"), "菜单-1", Id.id(""), false, "1"));
        authorities.get(new Authority(Id.id("UseCase-2-1"), "菜单-2-1", Id.id("UseCase-1"), true, "4"));
        authorities.get(new Authority(Id.id("UseCase-2-3"), "菜单-2-3", Id.id("UseCase-1"), true, "5"));
        authorities.get(new Authority(Id.id("UseCase-2-2"), "菜单-2-2", Id.id("UseCase-1"), true, "6"));
    }

    @Test
    @WithGrantedAuthorities(ltd.highsoft.hare.frameworks.security.core.Authorities.AUTHENTICATED)
    public void should_be_able_to_search_features() {
        var response = get("/authorities", document());
        response.statusCode(is(200));
        response.body("[0].id", is("UseCase-1"));
        response.body("[0].parentId", is(""));
        response.body("[0].name", is("菜单-1"));
        response.body("[0].children.size()", is(3));
        response.body("[0].children[1].id", is("UseCase-2-3"));
    }

    @AfterEach
    public void tearDown() {
        authorities.clear();
    }

    @Override
    protected Documentation document() {
        return Documentation.doc("authorities.get",
            responseFields(
                fieldWithPath("[].id").description("ID"),
                fieldWithPath("[].parentId").description("父ID"),
                fieldWithPath("[].name").description("名称"),
                fieldWithPath("[].isLeaf").description("是否有子数据"),
                fieldWithPath("[].children").description("子数据"),
                fieldWithPath("[].children[].id").description("子数据ID"),
                fieldWithPath("[].children[].parentId").description("子数据父ID"),
                fieldWithPath("[].children[].name").description("子数据名称"),
                fieldWithPath("[].children[].isLeaf").description("子数据内是否有子数据"),
                fieldWithPath("[].children[].children").description("子数据内的子数据")
            )
        );
    }

}
