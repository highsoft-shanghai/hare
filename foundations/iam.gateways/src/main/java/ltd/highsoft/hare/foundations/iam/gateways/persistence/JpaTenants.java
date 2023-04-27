package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.persistence.EntityManager;
import ltd.highsoft.hare.foundations.iam.domain.Tenant;
import ltd.highsoft.hare.foundations.iam.domain.Tenants;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.persistence.jpa.JpaRepository;
import ltd.highsoft.hare.frameworks.persistence.jpa.Specifications;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class JpaTenants implements Tenants {

    private final JpaRepository<JpaTenant, Tenant> repository;

    public JpaTenants(EntityManager entityManager) {
        this.repository = new JpaRepository<>(entityManager, JpaTenant.class, JpaTenant::new, JpaTenant::asDomain);
    }

    @Override
    @Transactional
    public void add(Tenant tenant) {
        repository.save(tenant);
    }

    @Override
    @Transactional
    public void remove(Id id) {
        repository.delete(id);
    }

    @Override
    public List<Tenant> get() {
        return repository.list(Specifications.all());
    }

    @Override
    @Transactional(readOnly = true)
    public Tenant get(Id id) {
        return repository.load(id);
    }

}
