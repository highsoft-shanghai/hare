package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Payload;

@UseCase(requiredAuthorities = "iam.update-role")
public class UpdateRoleUseCase {
    private final Roles roles;

    public UpdateRoleUseCase(Roles roles) {
        this.roles = roles;
    }

    public void execute(Id id, Payload payload) {
        Role role = roles.get(id);
        role.update(payload);
        roles.add(role);
    }

}
