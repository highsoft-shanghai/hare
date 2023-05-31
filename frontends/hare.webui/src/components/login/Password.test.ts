import {describe, expect, it} from '@jest/globals';
import {initializePayloadFactories} from 'commons/payload/FactoriesInitializer';
import {Password} from 'components/login/Password';

initializePayloadFactories();

describe('Password', () => {
  it('should be able to deserialize from string', () => {
    const password = new Password();
    password.fromData('new value');
    expect(password).toEqual(new Password('new value'));
  });
});
