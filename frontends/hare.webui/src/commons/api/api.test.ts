import {describe, expect, it} from '@jest/globals';
import {api} from 'commons/api/api';

describe('api', () => {
  it('should request with root prefix', () => {
    expect(api.getUri({url: '/users'})).toBe('/api/users');
  });
});
