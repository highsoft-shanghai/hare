import {describe, expect, it} from '@jest/globals';
import {success} from 'commons/security/AuthorizationResult';

describe('AuthorizationResult', () => {
  it('should be able to represent successful authorization', () => {
    const authorizationResult = success();
    expect(authorizationResult.approved).toBeTruthy();
    expect(authorizationResult.redirect).toBeUndefined();
  });
});
