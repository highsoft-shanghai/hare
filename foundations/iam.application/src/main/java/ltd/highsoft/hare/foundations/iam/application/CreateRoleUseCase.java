package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.RoleFactory;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.domain.core.Payload;

import static ltd.highsoft.hare.frameworks.context.core.GlobalUserContext.currentTenantId;

@UseCase(requiredAuthorities = "iam.create-role")
public class CreateRoleUseCase {

    private final Roles roles;
    private final RoleFactory roleFactory;

    public CreateRoleUseCase(Roles roles, RoleFactory roleFactory) {
        this.roles = roles;
        this.roleFactory = roleFactory;
    }

    public Object execute(Payload payload) {
        Role role = roleFactory.newRole(payload, currentTenantId());
        roles.add(role);
        return role.id().id().asString();
    }

}
