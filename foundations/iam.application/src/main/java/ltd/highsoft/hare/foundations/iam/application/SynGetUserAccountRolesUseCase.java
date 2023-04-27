package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.foundations.iam.domain.UserAccounts;
import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.ValueSink;
import ltd.highsoft.hare.frameworks.domain.core.ValueSinkFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UseCase(requiredAuthorities = {})
public class SynGetUserAccountRolesUseCase {

    private final UserAccounts userAccounts;
    private final Roles roles;
    private final ValueSinkFactory valueSinkFactory;

    public SynGetUserAccountRolesUseCase(UserAccounts userAccounts, Roles roles, ValueSinkFactory valueSinkFactory) {
        this.userAccounts = userAccounts;
        this.roles = roles;
        this.valueSinkFactory = valueSinkFactory;
    }

    public Object execute(Id id) {
        Set<String> roleIds = userAccounts.get(id).roles().asSet();
        List<Role> roleList = roleIds.stream().map(roleId -> roles.get(Id.id(roleId))).collect(Collectors.toList());
        return valueSinkFactory.newValueSink(sink -> write(roleList, sink));
    }

    public void write(List<Role> values, ValueSink sink) {
        values.forEach(value -> value.selectableContent(sink.addObject()));
    }

}
