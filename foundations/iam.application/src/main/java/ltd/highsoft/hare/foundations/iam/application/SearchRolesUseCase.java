package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.domain.core.Page;
import ltd.highsoft.hare.frameworks.domain.core.Pagination;
import ltd.highsoft.hare.frameworks.domain.core.ValueSinkFactory;

import static ltd.highsoft.hare.frameworks.context.core.GlobalUserContext.currentTenantId;

@UseCase(requiredAuthorities = "iam.search-roles")
public class SearchRolesUseCase {

    private final Roles roles;
    private final ValueSinkFactory valueSinkFactory;

    public SearchRolesUseCase(Roles roles, ValueSinkFactory valueSinkFactory) {
        this.roles = roles;
        this.valueSinkFactory = valueSinkFactory;
    }

    public Object execute(String q, Pagination pagination) {
        Page<Role> rolePage = roles.search(currentTenantId(), q, pagination);
        return rolePage.map(role -> valueSinkFactory.newObjectSink(role::content));
    }

}
