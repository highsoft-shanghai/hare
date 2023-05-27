import {describe, expect, it} from '@jest/globals';
import {Authorizer} from 'commons/security/Authorizer';
import {requiredAuthorities} from 'commons/security/RequiredAuthorities';
import {success} from 'commons/security/AuthorizationResult';
import {grantedAuthorities} from 'commons/security/GrantedAuthorities';

describe('Authorizer', () => {
  it('should allow authorized access', () => {
    const authorizer = new Authorizer(grantedAuthorities('user.add'));
    expect(authorizer.authorize(requiredAuthorities('user.add'))).toStrictEqual(success());
  });
});
