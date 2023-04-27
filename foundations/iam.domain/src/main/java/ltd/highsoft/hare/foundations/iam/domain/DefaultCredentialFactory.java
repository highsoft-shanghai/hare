package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.IdGenerator;

public class DefaultCredentialFactory implements CredentialFactory {

    private final IdGenerator idGenerator;

    public DefaultCredentialFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public Credential newCredential(CredentialOwner owner, CredentialType type, LoginName loginName, EncryptedSecret secret) {
        return new Credential(idGenerator.nextId(), owner, type, loginName, secret, false);
    }

}
