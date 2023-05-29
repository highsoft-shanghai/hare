import {beforeEach, describe, expect, it} from '@jest/globals';
import {Context} from 'commons/context/Context';
import {requiredAuthorities} from 'commons/context/RequiredAuthorities';
import {forbidden, redirectToLogin, success} from 'commons/context/AuthorizationResult';
import {Authorities} from 'commons/context/Authorities';
import {mock} from 'jest-mock-extended';
import {ContextApi} from 'commons/context/ContextApi';
import {payload} from 'commons/payload/Payload';
import {SessionStorage} from 'quasar';
import {globals} from 'commons/global/globals';
import {fail} from 'assert';
import {installGlobalStorage} from 'commons/global/QuasarStorage';

describe('Context', () => {
  let context: Context, mockContextApi: ReturnType<typeof mock<ContextApi>>;

  beforeEach(() => {
    installGlobalStorage();
    mockContextApi = mock<ContextApi>();
    mockContextApi.get.calledWith().mockReturnValue(payload({grantedAuthorities: ['user.add']}));
    context = new Context(mockContextApi);
  });

  it('should allow authorized access', async () => {
    await context.reset('access-token.test');
    expect(await context.authorize(requiredAuthorities('user.add'))).toStrictEqual(success());
  });

  it('should disallow unauthorized access', async () => {
    await context.reset('access-token.test');
    expect(await context.authorize(requiredAuthorities('user.update'))).toStrictEqual(forbidden());
  });

  it('should redirect to login when current user is not authenticated but authentication required', async () => {
    context.clear();
    expect(await context.authorize(requiredAuthorities(Authorities.AUTHENTICATED))).toStrictEqual(redirectToLogin());
  });

  it('should be able to restore from storage', async () => {
    globals.storage.set('accessToken', 'token.test');
    await context.restore();
    expect(context.accessToken).toBe('token.test');
    expect(mockContextApi.get).toBeCalled();
  });
});
