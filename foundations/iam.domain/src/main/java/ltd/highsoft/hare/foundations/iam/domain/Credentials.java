package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;

import java.util.*;

public interface Credentials {

    Optional<Credential> credentialFor(CredentialType type, String loginName);

    void add(Credential credential);

    void remove(Id id);

}
