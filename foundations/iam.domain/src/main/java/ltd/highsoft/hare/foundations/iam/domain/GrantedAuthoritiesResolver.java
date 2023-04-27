package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;

public interface GrantedAuthoritiesResolver {

    GrantedAuthorities resolve(Id userAccountId);

}
