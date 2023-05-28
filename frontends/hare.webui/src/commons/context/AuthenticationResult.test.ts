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
    expect(result.message).toBeUndefined();
  });

  it('should be able to represent failed authentication', () => {
    const result = authenticationResult(payload({success: false, message: 'message-from-server'}));
    expect(result.success).toBeFalsy();
    expect(result.accessToken).toBeUndefined();
    expect(result.message).toBe('message-from-server');
  });

  it('should reject malformed payloads', () => {
    expect(() => authenticationResult(payload({success: true}))).toThrow('Malformed authentication result payload from server');
    expect(() => authenticationResult(payload({success: false}))).toThrow('Malformed authentication result payload from server');
  });

  it('should cleanup abnormal payloads with redundant access-token', () => {
    const result = authenticationResult(payload({success: false, accessToken: 'abnormal-token', message: 'message-from-server'}));
    expect(result.success).toBeFalsy();
    expect(result.accessToken).toBeUndefined();
  });
});
