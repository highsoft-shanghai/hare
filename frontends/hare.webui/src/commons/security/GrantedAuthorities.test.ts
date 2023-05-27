import {describe, expect, it} from '@jest/globals';
import {grantedAuthorities} from 'commons/security/GrantedAuthorities';

describe('GrantedAuthorities', () => {
  it('should be authenticated in normal', () => {
    expect(grantedAuthorities('user.add').authorities.has('authenticated')).toBeTruthy();
  });
});
