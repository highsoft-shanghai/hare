package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;

public interface Users {

    void add(User user);

    User get(Id id);

    void remove(Id id);

}
