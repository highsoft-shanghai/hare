import {RequiredAuthorities} from 'commons/security/RequiredAuthorities';
import {AuthorizationResult, forbidden, redirectToLogin, success} from 'commons/security/AuthorizationResult';
import {GrantedAuthorities} from 'commons/security/GrantedAuthorities';

export class Authorizer {
  private readonly grantedAuthorities: GrantedAuthorities;

  public constructor(grantedAuthorities: GrantedAuthorities) {
    this.grantedAuthorities = grantedAuthorities;
  }

  public authorize(requiredAuthorities: RequiredAuthorities): AuthorizationResult {
    if (requiredAuthorities.matches(this.grantedAuthorities)) return success();
    if (this.grantedAuthorities.anonymous) return redirectToLogin();
    return forbidden();
  }
}
