import {describe, expect, it} from '@jest/globals';
import {forbidden, redirectToLogin, success} from 'commons/security/AuthorizationResult';

describe('AuthorizationResult', () => {
  it('should be able to represent successful authorization', () => {
    const authorizationResult = success();
    expect(authorizationResult.approved).toBeTruthy();
    expect(authorizationResult.redirect).toBeUndefined();
  });

  it('should be able to represent forbidden authorization', () => {
    const authorizationResult = forbidden();
    expect(authorizationResult.approved).toBeFalsy();
    expect(authorizationResult.redirect).toBeFalsy();
  });

  it('should be able to represent redirect-to-login authorization', () => {
    const authorizationResult = redirectToLogin();
    expect(authorizationResult.approved).toBeFalsy();
    expect(authorizationResult.redirect).toStrictEqual({name: 'route.login'});
  });
});
