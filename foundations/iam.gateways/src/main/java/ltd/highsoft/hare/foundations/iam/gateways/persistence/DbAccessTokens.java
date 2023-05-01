package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.AccessToken;
import ltd.highsoft.hare.foundations.iam.domain.AccessTokens;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DbAccessTokens implements AccessTokens {

    private @Resource AccessTokenMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<AccessToken> getOptional(Id id) {
        return mapper.getOptional(id);
    }

    @Override
    @Transactional
    public void add(AccessToken accessToken) {
        mapper.add(accessToken);
    }

    @Override
    @Transactional
    public void remove(Id id) {
        mapper.remove(id);
    }

    @Override
    @Transactional
    public void removeAll(Id userAccountId, String group) {
        mapper.removeAll(userAccountId, group);
    }

}
