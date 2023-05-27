import {describe, expect, it} from '@jest/globals';
import {GrantedAuthorities, grantedAuthorities} from 'commons/security/GrantedAuthorities';

describe('GrantedAuthorities', () => {
  it('should be authenticated when created in normal', () => {
    expect(grantedAuthorities('user.add').authorities.has('authenticated')).toBeTruthy();
    expect(grantedAuthorities('user.add').authenticated).toBeTruthy();
  });

  it('should be anonymous when created as anonymous', () => {
    expect(GrantedAuthorities.ANONYMOUS.authorities.has('anonymous')).toBeTruthy();
    expect(GrantedAuthorities.ANONYMOUS.anonymous).toBeTruthy();
  });
});
