package ltd.highsoft.hare.foundations.iam.gateways.config;

import ltd.highsoft.hare.foundations.iam.domain.*;
import ltd.highsoft.hare.frameworks.domain.core.IdGenerator;
import ltd.highsoft.hare.frameworks.domain.core.PasswordEncoder;
import ltd.highsoft.hare.frameworks.security.core.ContextProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IamConfiguration {

    @Bean
    public ContextProvider contextProvider(AccessTokens accessTokens) {
        return new AccessTokenContextProvider(accessTokens);
    }

    @Bean
    public UserAccountFactory userAccountFactory(IdGenerator idGenerator, Roles roles) {
        return new UserAccountFactory(idGenerator, roles);
    }

    @Bean
    public RoleFactory roleFactory(IdGenerator idGenerator) {
        return new RoleFactory(idGenerator);
    }

    @Bean
    public CredentialMatcher credentialMatcher(Credentials credentials, PasswordEncoder passwordEncoder) {
        return new SelectiveCredentialMatcher(credentials, passwordEncoder);
    }

    @Bean
    public LoginFactory loginFactory(IdGenerator idGenerator, CredentialMatcher matcher, AccessTokenStore tokenStore, AccessTokenFactory accessTokenFactory) {
        return new LoginFactory(idGenerator, matcher, tokenStore, accessTokenFactory);
    }

    @Bean
    public AccessTokenFactory accessTokenFactory(IdGenerator idGenerator, IdentityFetcher identityFetcher, GrantedAuthoritiesResolver authoritiesResolver) {
        return new AccessTokenFactory(idGenerator, authoritiesResolver, identityFetcher);
    }

    @Bean
    public IdentityFetcher identityFetcher(UserAccounts userAccounts, Users users, Tenants tenants) {
        return new DefaultIdentityFetcher(userAccounts, users, tenants);
    }

    @Bean
    public AccessTokenStore accessTokenStore(AccessTokens accessTokens) {
        return new AccessTokenStore(accessTokens);
    }

    @Bean
    public CredentialFactory credentialFactory(IdGenerator idGenerator) {
        return new DefaultCredentialFactory(idGenerator);
    }

    @Bean
    public GrantedAuthoritiesResolver grantedAuthoritiesResolver(UserAccounts userAccounts) {
        return new UserAccountGrantedAuthoritiesResolver(userAccounts);
    }

}
