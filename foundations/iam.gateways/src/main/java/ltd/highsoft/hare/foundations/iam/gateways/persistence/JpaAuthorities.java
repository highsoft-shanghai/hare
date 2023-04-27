package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.persistence.EntityManager;
import ltd.highsoft.hare.foundations.iam.domain.Authorities;
import ltd.highsoft.hare.foundations.iam.domain.Authority;
import ltd.highsoft.hare.frameworks.persistence.jpa.JpaRepository;
import ltd.highsoft.hare.frameworks.persistence.jpa.Specifications;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class JpaAuthorities implements Authorities {

    private final JpaRepository<JpaAuthority, Authority> repository;

    public JpaAuthorities(EntityManager entityManager) {
        this.repository = new JpaRepository<>(entityManager, JpaAuthority.class, JpaAuthority::new, JpaAuthority::asDomain);
    }

    @Override
    @Transactional
    public void get(Authority authority) {
        repository.save(authority);
    }

    @Override
    public List<Authority> all() {
        return repository.list(Specifications.all());

    }

    @Override
    @Transactional
    public void clear() {
        repository.deleteAll(Specifications.all());
    }

}
