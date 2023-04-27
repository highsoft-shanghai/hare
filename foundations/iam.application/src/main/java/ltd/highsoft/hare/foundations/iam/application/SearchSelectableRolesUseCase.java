package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.domain.core.ValueSink;
import ltd.highsoft.hare.frameworks.domain.core.ValueSinkFactory;

import java.util.List;

import static ltd.highsoft.hare.frameworks.context.core.GlobalUserContext.currentTenantId;

@UseCase(requiredAuthorities = {})
public class SearchSelectableRolesUseCase {

    private final Roles roles;
    private final ValueSinkFactory valueSinkFactory;

    public SearchSelectableRolesUseCase(Roles roles, ValueSinkFactory valueSinkFactory) {
        this.roles = roles;
        this.valueSinkFactory = valueSinkFactory;
    }

    public ValueSink execute(String q) {
        return valueSinkFactory.newValueSink(sink -> write(roles.search(currentTenantId(), q), sink));
    }

    public void write(List<Role> values, ValueSink sink) {
        values.forEach(value -> value.selectableContent(sink.addObject()));
    }

}
