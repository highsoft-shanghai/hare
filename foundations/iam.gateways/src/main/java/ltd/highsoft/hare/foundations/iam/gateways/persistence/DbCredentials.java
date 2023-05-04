package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.Credential;
import ltd.highsoft.hare.foundations.iam.domain.CredentialType;
import ltd.highsoft.hare.foundations.iam.domain.Credentials;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class DbCredentials implements Credentials {

    private @Resource CredentialMapper mapper;

    @Override
    public Optional<Credential> credentialFor(CredentialType type, String loginName) {
        return mapper.credentialFor(type, loginName);
    }

    @Override
    public void add(Credential credential) {
        mapper.add(credential);
    }

    @Override
    public void remove(Id id) {
        mapper.remove(id);
    }

}
