import {describe, expect, it} from '@jest/globals';
import {Context} from 'commons/context/Context';
import {requiredAuthorities} from 'commons/context/RequiredAuthorities';
import {forbidden, redirectToLogin, success} from 'commons/context/AuthorizationResult';
import {GrantedAuthorities, grantedAuthorities} from 'commons/context/GrantedAuthorities';
import {Authorities} from 'commons/context/Authorities';

describe('Context', () => {
  it('should allow authorized access', () => {
    const context = new Context(grantedAuthorities('user.add'));
    expect(context.authorize(requiredAuthorities('user.add'))).toStrictEqual(success());
  });

  it('should disallow unauthorized access', () => {
    const context = new Context(grantedAuthorities('user.add'));
    expect(context.authorize(requiredAuthorities('user.update'))).toStrictEqual(forbidden());
  });

  it('should redirect to login when current user is not authenticated but authentication required', () => {
    const context = new Context(GrantedAuthorities.ANONYMOUS);
    expect(context.authorize(requiredAuthorities(Authorities.AUTHENTICATED))).toStrictEqual(redirectToLogin());
  });
});
