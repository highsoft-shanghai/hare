import {describe, expect, it} from '@jest/globals';
import {LoginName} from 'components/login/LoginName';
import {initializePayloadFactories} from 'commons/payload/FactoriesInitializer';

initializePayloadFactories();

describe('LoginName', () => {
  it('should be able to deserialize from string', () => {
    const loginName = new LoginName();
    loginName.assign('new value');
    expect(loginName).toEqual(new LoginName('new value'));
  });
});
