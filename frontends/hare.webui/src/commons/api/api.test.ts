import {beforeEach, describe, expect, it} from '@jest/globals';
import {api} from 'commons/api/api';
import MockAdapter from 'axios-mock-adapter';
import {resettableGlobals} from 'commons/global/globals';
import {Context} from 'commons/context/Context';
import {mock} from 'jest-mock-extended';

describe('api', () => {
  let mockApi: MockAdapter;
  let mockContext: ReturnType<typeof mock<Context>>;

  beforeEach(() => {
    mockApi = new MockAdapter(api);
    mockContext = mock<Context>();
    resettableGlobals.resetContext(mockContext);
    mockApi.onAny().reply(200);
  });

  it('should request with root prefix', () => {
    expect(api.getUri({url: '/users'})).toBe('/api/users');
  });

  it('should request with authorization headers when context is not anonymous', async () => {
    Object.defineProperty(mockContext, 'accessToken', {get: () => 'token.test'});
    await api.get('/test');
    expect(mockApi.history.get[0].headers).toHaveProperty('Authorization', 'Bearer token.test');
  });

  it('should request without authorization headers when context is anonymous', async () => {
    Object.defineProperty(mockContext, 'accessToken', {get: () => undefined});
    await api.get('/test');
    expect(mockApi.history.get[0].headers?.Authorization).toBeUndefined();
  });
});
