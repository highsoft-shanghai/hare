package ltd.highsoft.frameworks.security.core;

import com.google.common.collect.Sets;
import lombok.EqualsAndHashCode;
import ltd.highsoft.frameworks.domain.core.*;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.*;

import static ltd.highsoft.frameworks.domain.core.I18nMessage.message;

@EqualsAndHashCode
public final class GrantedAuthorities {

    public static final GrantedAuthorities ANONYMOUS = GrantedAuthorities.of(Authorities.ANONYMOUS);
    public static final GrantedAuthorities SYSTEM = GrantedAuthorities.of(Authorities.SYSTEM);
    private final Set<String> authorities;

    public static GrantedAuthorities of(String... authorities) {
        return new GrantedAuthorities(authorities);
    }

    public static GrantedAuthorities of(Set<String> authorities) {
        return new GrantedAuthorities(authorities);
    }

    public static GrantedAuthorities fromCommaSeparatedString(String value) {
        return new GrantedAuthorities(StringUtils.split(value, ", "));
    }

    public static GrantedAuthorities of(Stream<GrantedAuthorities> stream) {
        return GrantedAuthorities.of(stream.map(GrantedAuthorities::asSet).flatMap(Collection::stream).collect(Collectors.toSet()));
    }

    public String toCommaSeparatedString() {
        return String.join(",", authorities);
    }

    public void authorize(RequiredAuthorities requiredAuthorities) {
        if (match(requiredAuthorities)) return;
        if (isAnonymous()) throw new AuthenticationException(message("error.authentication-required"));
        throw new AuthorizationException(message("error.access-denied", requiredAuthorities, this));
    }

    public Set<String> asSet() {
        return authorities;
    }

    public boolean isAnonymous() {
        return authorities.contains(Authorities.ANONYMOUS);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + authorities.stream().sorted().collect(Collectors.joining(", ")) + ")";
    }

    private GrantedAuthorities(String... grantedAuthorities) {
        this.authorities = new LinkedHashSet<>(Arrays.asList(grantedAuthorities));
    }

    private GrantedAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    private boolean match(RequiredAuthorities requiredAuthorities) {
        if (isSpecialRole()) return true;
        if (requiredAuthorities.requireAnonymous()) return true;
        if (requiredAuthorities.requireAuthenticatedOnly() && !isAnonymous()) return true;
        return !Sets.intersection(authorities, requiredAuthorities.asSet()).isEmpty();
    }

    private boolean isSpecialRole() {
        return authorities.contains(Authorities.ADMIN) || authorities.contains(Authorities.SYSTEM);
    }

    public void content(ValueSink valueSink) {
        this.authorities.forEach(valueSink::add);
    }

}
