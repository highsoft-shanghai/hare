package ltd.highsoft.frameworks.security.core;

import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class RequiredAuthorities {

    public static final RequiredAuthorities ANONYMOUS = RequiredAuthorities.of(Authorities.ANONYMOUS);
    public static final RequiredAuthorities AUTHENTICATED = RequiredAuthorities.of(Authorities.AUTHENTICATED);
    private final Set<String> authorities;

    public static RequiredAuthorities of(String... authorities) {
        return new RequiredAuthorities(authorities);
    }

    public RequiredAuthorities(String[] authorities) {
        this.authorities = Set.of(authorities);
    }

    public Set<String> asSet() {
        return authorities;
    }

    public boolean requireAnonymous() {
        return authorities.contains(Authorities.ANONYMOUS);
    }

    public boolean requireAuthenticatedOnly() {
        return (authorities.isEmpty() || authorities.contains(Authorities.AUTHENTICATED)) && !requireAnonymous();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + authorities.stream().sorted().collect(Collectors.joining(", ")) + ")";
    }

}
