package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Code;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.IdGenerator;
import ltd.highsoft.hare.frameworks.domain.core.Payload;

import static ltd.highsoft.hare.frameworks.domain.core.ScopedId.id;

public class RoleFactory {

    private final IdGenerator idGenerator;

    public RoleFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Role newRole(Payload payload, Id tenantId) {
        return new Role(id(idGenerator.nextId(), tenantId), Code.code(idGenerator.nextId().asString()), payload);
    }

}
