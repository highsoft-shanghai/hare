import {describe, expect, it} from '@jest/globals';
import {initializePayloadFactories} from 'commons/payload/FactoriesInitializer';
import {Password} from 'components/login/Password';

initializePayloadFactories();

describe('Password', () => {
  it('should be able to deserialize from string', () => {
    const password = new Password();
    password.assign('new value');
    expect(password).toEqual(new Password('new value'));
  });

  it('should hash underlying text when serialize to json', () => {
    const password = new Password('123456');
    expect(password.toJSON()).toBe('ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413');
  });

  it('should serialize to empty string if value is empty', () => {
    expect(new Password().toJSON()).toBeNull();
    expect(new Password('').toJSON()).toBeNull();
  });
});
