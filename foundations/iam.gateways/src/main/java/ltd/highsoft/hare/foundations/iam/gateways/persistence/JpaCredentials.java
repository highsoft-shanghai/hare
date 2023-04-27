package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.persistence.EntityManager;
import ltd.highsoft.hare.foundations.iam.domain.Credential;
import ltd.highsoft.hare.foundations.iam.domain.CredentialType;
import ltd.highsoft.hare.foundations.iam.domain.Credentials;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.persistence.jpa.JpaRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class JpaCredentials implements Credentials {

    private final JpaRepository<JpaCredential, Credential> repository;

    public JpaCredentials(EntityManager entityManager) {
        this.repository = new JpaRepository<>(entityManager, JpaCredential.class, JpaCredential::new, JpaCredential::asDomain);
    }

    @Override
    public Optional<Credential> credentialFor(CredentialType type, String loginName) {
        return repository.loadOptional(withType(type).and(withLoginName(loginName)));
    }

    @Override
    public void add(Credential credential) {
        repository.save(credential);
    }

    @Override
    public void remove(Id id) {
        repository.delete(id);
    }

    @Override
    public void removeForUserAccount(Id userAccountId) {
        repository.deleteAll(withUserAccount(userAccountId.asString()));
    }

    @Override
    public List<Credential> getForUserAccount(Id userAccountId) {
        return repository.list(withUserAccount(userAccountId.asString()));
    }

    private static Specification<JpaCredential> withUserAccount(String userAccountId) {
        return (r, q, b) -> b.equal(r.get("userAccountId"), userAccountId);
    }

    private static Specification<JpaCredential> withLoginName(String loginName) {
        return (r, q, b) -> b.equal(r.get("loginName"), loginName);
    }

    private Specification<JpaCredential> withType(CredentialType type) {
        return (root, query, builder) -> builder.equal(root.get("type"), type.asString());
    }

}
