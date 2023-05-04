package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.domain.core.Id;

import static ltd.highsoft.hare.frameworks.context.core.GlobalUserContext.currentTenantId;
import static ltd.highsoft.hare.frameworks.domain.core.ScopedId.id;

@UseCase(requiredAuthorities = "iam.update-role")
public class GetRoleForUpdatingUseCase {
    private final Roles roles;

    public GetRoleForUpdatingUseCase(Roles roles) {
        this.roles = roles;
    }

    public RoleContent execute(Id id) {
        Role role = roles.get(id(id, currentTenantId()));
        return RoleContent.build(role);
    }

}
