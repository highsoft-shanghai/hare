package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;

public interface UserAccounts {

    void add(UserAccount userAccount);

    UserAccount get(Id id);

    void remove(Id id);

    boolean existsByRoleId(String roleId);

}
