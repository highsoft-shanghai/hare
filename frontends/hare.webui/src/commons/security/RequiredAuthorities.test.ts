import {describe, expect, it} from '@jest/globals';
import {requiredAuthorities} from 'commons/security/RequiredAuthorities';
import {grantedAuthorities} from 'commons/security/GrantedAuthorities';

describe('RequiredAuthorities', () => {
  it('should match granted authorities when same authority found in both sides', () => {
    expect(requiredAuthorities('user.add').matches(grantedAuthorities('user.add'))).toBeTruthy();
    expect(requiredAuthorities('user.update').matches(grantedAuthorities('user.search', 'user.update'))).toBeTruthy();
    expect(requiredAuthorities('user.update', 'project.update').matches(grantedAuthorities('user.search', 'user.update'))).toBeTruthy();
  });

  it('should mismatch granted authorities when no same authority found in both sides', () => {
    expect(requiredAuthorities('user.add').matches(grantedAuthorities('user.update'))).toBeFalsy();
    expect(requiredAuthorities('user.add', 'user.update').matches(grantedAuthorities('user.search', 'user.delete'))).toBeFalsy();
  });
});
