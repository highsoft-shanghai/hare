import {RequiredAuthorities} from 'commons/security/RequiredAuthorities';
import {AuthorizationResult, forbidden, success} from 'commons/security/AuthorizationResult';
import {GrantedAuthorities} from 'commons/security/GrantedAuthorities';

export class Authorizer {
  private readonly grantedAuthorities: GrantedAuthorities;

  public constructor(grantedAuthorities: GrantedAuthorities) {
    this.grantedAuthorities = grantedAuthorities;
  }

  public authorize(requiredAuthorities: RequiredAuthorities): AuthorizationResult {
    if (requiredAuthorities.authorities.some(x => this.grantedAuthorities.authorities.has(x))) return success();
    return forbidden();
  }
}
