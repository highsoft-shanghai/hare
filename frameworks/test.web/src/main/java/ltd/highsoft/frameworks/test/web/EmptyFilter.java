package ltd.highsoft.frameworks.test.web;

import io.restassured.filter.*;
import io.restassured.response.Response;
import io.restassured.specification.*;

public class EmptyFilter implements Filter {

    public static final Filter INSTANCE = new EmptyFilter();

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        return ctx.next(requestSpec, responseSpec);
    }

}
