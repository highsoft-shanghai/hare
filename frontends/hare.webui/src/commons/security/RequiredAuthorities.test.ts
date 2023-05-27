import {describe, expect, it} from '@jest/globals';
import {requiredAuthorities} from 'commons/security/RequiredAuthorities';
import {grantedAuthorities} from 'commons/security/GrantedAuthorities';

describe('RequiredAuthorities', () => {
  it('should match granted authorities which contains any authority in both sides', () => {
    expect(requiredAuthorities('user.add').matches(grantedAuthorities('user.add'))).toBeTruthy();
  });
});
