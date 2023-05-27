import {describe, expect, it} from '@jest/globals';
import {Authorizer} from 'commons/security/Authorizer';
import {requiredAuthorities} from 'commons/security/RequiredAuthorities';
import {forbidden, redirectToLogin, success} from 'commons/security/AuthorizationResult';
import {GrantedAuthorities, grantedAuthorities} from 'commons/security/GrantedAuthorities';
import {Authorities} from 'commons/security/Authorities';

describe('Authorizer', () => {
  it('should allow authorized access', () => {
    const authorizer = new Authorizer(grantedAuthorities('user.add'));
    expect(authorizer.authorize(requiredAuthorities('user.add'))).toStrictEqual(success());
  });

  it('should disallow unauthorized access', () => {
    const authorizer = new Authorizer(grantedAuthorities('user.add'));
    expect(authorizer.authorize(requiredAuthorities('user.update'))).toStrictEqual(forbidden());
  });

  it('should redirect to login when current user is not authenticated but authentication required', () => {
    const authorizer = new Authorizer(GrantedAuthorities.ANONYMOUS);
    expect(authorizer.authorize(requiredAuthorities(Authorities.AUTHENTICATED))).toStrictEqual(redirectToLogin());
  });
});
