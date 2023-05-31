import {describe, expect, it} from '@jest/globals';
import {Username} from 'components/login/Username';
import {initializePayloadFactories} from 'commons/payload/FactoriesInitializer';

initializePayloadFactories();

describe('Username', () => {
  it('should be able to deserialize from string', () => {
    const loginName = new Username();
    loginName.assign('new value');
    expect(loginName).toEqual(new Username('new value'));
  });
});
