package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.*;
import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.PasswordEncoder;
import ltd.highsoft.hare.frameworks.security.core.Authorities;

import java.util.Map;
import java.util.Set;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;

@UseCase(requiredAuthorities = Authorities.AUTHENTICATED)
public class SynUserAccountUseCase {

    private final UserAccounts userAccounts;
    private final UserAccountFactory userAccountFactory;
    private final CredentialFactory credentialFactory;
    private final Credentials credentials;

    public SynUserAccountUseCase(UserAccounts userAccounts, UserAccountFactory userAccountFactory, CredentialFactory credentialFactory, Credentials credentials, PasswordEncoder passwordEncoder) {
        this.userAccounts = userAccounts;
        this.userAccountFactory = userAccountFactory;
        this.credentialFactory = credentialFactory;
        this.credentials = credentials;
    }

    public void execute(Id id, String name, Id tenantId, Map<String, CredentialDescription> map, Set<String> roleIds) {
        UserAccount userAccount = userAccountFactory.newUserAccountWithId(id, name, new UserAccountOwner(id(""), tenantId), roleIds);
        userAccount.commit(credentialFactory, userAccounts, credentials, map);
    }

}
