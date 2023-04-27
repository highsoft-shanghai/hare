package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.persistence.EntityManager;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.foundations.iam.domain.UserAccount;
import ltd.highsoft.hare.foundations.iam.domain.UserAccounts;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.persistence.jpa.JpaRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class JpaUserAccounts implements UserAccounts {

    private final JpaRepository<JpaUserAccount, UserAccount> repository;

    public JpaUserAccounts(EntityManager entityManager, Roles roles) {
        this.repository = new JpaRepository<>(entityManager, JpaUserAccount.class, JpaUserAccount::new, data -> data.asDomain(roles));
    }

    @Override
    @Transactional
    public void add(UserAccount userAccount) {
        repository.save(userAccount);
    }

    @Override
    @Transactional
    public void remove(Id id) {
        repository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccount get(Id id) {
        return repository.load(id);
    }

    @Override
    public List<UserAccount> searchByRole(String roleId) {
        return repository.list(withRoleId(roleId));
    }

    private Specification<JpaUserAccount> withRoleId(String roleId) {
        return (root, query, builder) -> builder.isMember(roleId, root.get("roleIds"));
    }

}
