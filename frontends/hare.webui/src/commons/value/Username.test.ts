import {describe, expect, it} from '@jest/globals';
import {Username} from 'commons/value/Username';
import {initializePayloadFactories} from 'commons/payload/FactoriesInitializer';

initializePayloadFactories();

describe('Username', () => {
  it('should be able to deserialize from string', () => {
    const username = new Username();
    username.fromData('new value');
    expect(username).toEqual(new Username('new value'));
  });
});
