import {describe, expect, it} from '@jest/globals';
import {authenticationResult} from 'commons/context/AuthenticationResult';
import {payload} from 'commons/payload/Payload';
import {initializePayloadFactories} from 'commons/payload/FactoriesInitializer';

initializePayloadFactories();

describe('AuthenticationResult', () => {
  it('should be able to represent successful authentication', () => {
    const result = authenticationResult(payload({success: true, accessToken: 'token-from-server'}));
    expect(result.success).toBeTruthy();
    expect(result.accessToken).toBe('token-from-server');
  });
});
