package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.ObjectSink;
import ltd.highsoft.hare.frameworks.domain.core.ValueSinkFactory;

import static ltd.highsoft.hare.frameworks.context.core.GlobalUserContext.currentTenantId;
import static ltd.highsoft.hare.frameworks.domain.core.ScopedId.id;

@UseCase(requiredAuthorities = "iam.update-role")
public class GetRoleForUpdatingUseCase {
    private final Roles roles;
    private final ValueSinkFactory valueSinkFactory;

    public GetRoleForUpdatingUseCase(Roles roles, ValueSinkFactory valueSinkFactory) {
        this.roles = roles;
        this.valueSinkFactory = valueSinkFactory;
    }

    public ObjectSink execute(Id id) {
        return valueSinkFactory.newObjectSink(roles.get(id(id, currentTenantId()))::detailsContent);
    }

}
