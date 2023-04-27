package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.foundations.iam.domain.UserAccounts;
import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;

import static ltd.highsoft.hare.frameworks.context.core.GlobalUserContext.currentTenantId;

@UseCase(requiredAuthorities = "iam.delete-role")
public class DeleteRoleUseCase {
    private final Roles roles;
    private final UserAccounts userAccounts;

    public DeleteRoleUseCase(Roles roles, UserAccounts userAccounts) {
        this.roles = roles;
        this.userAccounts = userAccounts;
    }

    public void execute(String id) {
        Role role = roles.get(ScopedId.id(id, currentTenantId()));
        role.checkIsUse(userAccounts);
        roles.remove(ScopedId.id(id, currentTenantId()));
    }
}
