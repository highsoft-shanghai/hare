import {RequiredAuthorities} from 'commons/security/RequiredAuthorities';
import {AuthorizationResult, success} from 'commons/security/AuthorizationResult';
import {GrantedAuthorities} from 'commons/security/GrantedAuthorities';

export class Authorizer {
  public constructor(grantedAuthorities: GrantedAuthorities) {

  }

  public authorize(requiredAuthorities: RequiredAuthorities): AuthorizationResult {
    return success();
  }
}
