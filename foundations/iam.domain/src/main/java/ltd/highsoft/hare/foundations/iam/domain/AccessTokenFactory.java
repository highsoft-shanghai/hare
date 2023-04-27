package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.IdGenerator;

public class AccessTokenFactory {

    private final IdGenerator idGenerator;
    private final GrantedAuthoritiesResolver authoritiesResolver;
    private final IdentityFetcher identityFetcher;

    public AccessTokenFactory(IdGenerator idGenerator, GrantedAuthoritiesResolver authoritiesResolver, IdentityFetcher identityFetcher) {
        this.idGenerator = idGenerator;
        this.authoritiesResolver = authoritiesResolver;
        this.identityFetcher = identityFetcher;
    }

    public AccessToken newAccessToken(CredentialOwner credentialOwner, String group) {
        return new AccessToken(idGenerator.nextId(), accessTokenOwner(credentialOwner), authoritiesResolver.resolve(credentialOwner.userAccountId()), group);
    }

    private AccessTokenOwner accessTokenOwner(CredentialOwner credentialOwner) {
        return new AccessTokenOwner(
            identityFetcher.fetchUserAccount(credentialOwner.userAccountId()), identityFetcher.fetchUser(credentialOwner.userId()),
            identityFetcher.fetchTenant(credentialOwner.tenantId())
        );
    }

}
