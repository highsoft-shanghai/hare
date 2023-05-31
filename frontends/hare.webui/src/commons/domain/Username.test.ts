import {describe, expect, it} from '@jest/globals';
import {Username} from 'commons/domain/Username';

describe('Username', () => {
  it('should be able to serialize as string', async () => {
    const username = new Username('john');
    expect(username.asData()).toBe('john');
  });
});
