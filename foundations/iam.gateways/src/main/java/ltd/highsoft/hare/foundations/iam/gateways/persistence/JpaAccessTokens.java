package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.persistence.EntityManager;
import ltd.highsoft.hare.foundations.iam.domain.AccessToken;
import ltd.highsoft.hare.foundations.iam.domain.AccessTokens;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.persistence.jpa.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class JpaAccessTokens implements AccessTokens {

    private final JpaRepository<JpaAccessToken, AccessToken> repository;

    public JpaAccessTokens(EntityManager entityManager) {
        this.repository = new JpaRepository<>(entityManager, JpaAccessToken.class, JpaAccessToken::new, JpaAccessToken::asDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccessToken> getOptional(Id id) {
        return repository.loadOptional(id);
    }

    @Override
    @Transactional
    public void get(AccessToken accessToken) {
        repository.save(accessToken);
    }

    @Override
    @Transactional
    public void remove(Id id) {
        repository.delete(id);
    }

    @Override
    @Transactional
    public void removeAll(Id userAccountId, String group) {
        repository.deleteAll((r, q, b) -> b.and(b.equal(r.get("userAccountId"), userAccountId.asString()), b.equal(r.get("group"), group)));
    }

}
