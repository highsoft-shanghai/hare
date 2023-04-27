package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.persistence.EntityManager;
import ltd.highsoft.hare.foundations.iam.domain.User;
import ltd.highsoft.hare.foundations.iam.domain.Users;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.persistence.jpa.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JpaUsers implements Users {

    private final JpaRepository<JpaUser, User> repository;

    public JpaUsers(EntityManager entityManager) {
        this.repository = new JpaRepository<>(entityManager, JpaUser.class, JpaUser::new, JpaUser::asDomain);
    }

    @Override
    @Transactional
    public void add(User user) {
        repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User get(Id id) {
        return repository.load(id);
    }

    @Override
    @Transactional
    public void remove(Id id) {
        repository.delete(id);
    }

}

