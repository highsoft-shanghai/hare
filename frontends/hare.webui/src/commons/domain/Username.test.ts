import {describe, expect, it} from '@jest/globals';
import {Username} from 'commons/domain/Username';

describe('Username', () => {
  it('should be able to serialize as string', () => {
    const username = new Username('john');
    expect(username.asData()).toBe('john');
  });

  it('should be able to deserialize from string', () => {
    const username = new Username();
    username.setValueFromData('new value');
    expect(username).toEqual(new Username('new value'));
  });
});
