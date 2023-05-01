package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;

public record UserOwner(Id id, Users users) {

    public User get() {
        return users.get(id);
    }

}
