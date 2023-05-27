import {RequiredAuthorities} from 'commons/context/RequiredAuthorities';
import {AuthorizationResult, forbidden, redirectToLogin, success} from 'commons/context/AuthorizationResult';
import {GrantedAuthorities} from 'commons/context/GrantedAuthorities';

export class Context {
  private grantedAuthorities: GrantedAuthorities;

  public constructor(grantedAuthorities: GrantedAuthorities) {
    this.grantedAuthorities = grantedAuthorities;
  }

  public authorize(requiredAuthorities: RequiredAuthorities): AuthorizationResult {
    if (requiredAuthorities.matches(this.grantedAuthorities)) return success();
    if (this.grantedAuthorities.anonymous) return redirectToLogin();
    return forbidden();
  }

  public reset(grantedAuthorities: GrantedAuthorities): void {
    this.grantedAuthorities = grantedAuthorities;
  }

  public clear(): void {
    this.grantedAuthorities = GrantedAuthorities.ANONYMOUS;
  }
}
