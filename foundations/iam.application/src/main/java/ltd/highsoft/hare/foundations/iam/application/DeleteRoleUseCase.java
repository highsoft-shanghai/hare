package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;

import static ltd.highsoft.hare.frameworks.context.core.GlobalUserContext.currentTenantId;

@UseCase(requiredAuthorities = "iam.delete-role")
public class DeleteRoleUseCase {
    private final Roles roles;

    public DeleteRoleUseCase(Roles roles) {
        this.roles = roles;
    }

    public void execute(String id) {
        roles.remove(ScopedId.id(id, currentTenantId()));
    }
}
