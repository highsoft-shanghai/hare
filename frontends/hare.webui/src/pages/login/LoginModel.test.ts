import {beforeEach, describe, expect, it} from '@jest/globals';
import {LoginModel} from 'pages/login/LoginModel';
import {mock} from 'jest-mock-extended';
import {Navigator} from 'commons/router/Navigator';
import {Context} from 'commons/context/Context';
import {initializePayloadFactories} from 'commons/payload/FactoriesInitializer';
import {payload} from 'commons/payload/Payload';
import {authenticationResult} from 'commons/context/AuthenticationResult';
import MockAdapter from 'axios-mock-adapter';
import {api} from 'commons/api/api';
import {AxiosLoginApi} from 'pages/login/AxiosLoginApi';
import {initializeGlobals} from 'src/initialize';
import {App} from 'vue';
import {installGlobalCulture} from 'commons/i18n/VueCulture';
import {resettableGlobals} from 'commons/global/globals';

describe('LoginModel', () => {
  let mockApi: MockAdapter;
  let mockNavigator: ReturnType<typeof mock<Navigator>>;
  let mockContext: ReturnType<typeof mock<Context>>;
  let model: LoginModel;

  beforeEach(() => {
    installGlobalCulture();
    initializePayloadFactories();
    initializeGlobals(mock<App>());
    mockApi = new MockAdapter(api);
    mockNavigator = mock<Navigator>();
    mockContext = mock<Context>();
    resettableGlobals.resetContext(mockContext);
    model = new LoginModel(new AxiosLoginApi(), mockNavigator, mockContext);
  });

  it('should be un-submittable when both username and password are missing', () => {
    expect(model.submittable).toBeFalsy();
  });

  it('should be un-submittable when both username and password are present', () => {
    model.username.handleInput('john@highsoft.ltd');
    model.password.handleInput('simple-password');
    expect(model.submittable).toBeTruthy();
  });

  it('should redirect to home page if submit success', async () => {
    mockApi.onPost('/api/logins').reply(201, {success: true, accessToken: 'access-token.mock'});
    model.username.handleInput('john@highsoft.ltd');
    model.password.handleInput('simple-password');
    await model.submit();
    const hashedPassword = '5c2ec978137ca1bb72b8433ca13878d44744bb93b142c8f4755970576a3de7476c4d47b38c48cfc16a7b68955dc266836101e4c9f40399389014ff35db62c294';
    expect(mockApi.history.post[0].data).toEqual(JSON.stringify({group: 'web', type: 'username-and-password', username: 'john@highsoft.ltd', password: hashedPassword}));
    expect(mockContext.reset).toBeCalledWith('access-token.mock');
    expect(mockNavigator.goto).toBeCalledWith('route.home');
  });

  it('should report error when submit failed', async () => {
    mockApi.onPost('/api/logins').reply(201, {success: false, reason: 'message from server'});
    model.username.handleInput('john@highsoft.ltd');
    model.password.handleInput('simple-password');
    await model.submit();
    expect(mockContext.reset).not.toBeCalled();
    expect(mockNavigator.goto).not.toBeCalled();
    expect(model.lastAuthenticationResult).toEqual(authenticationResult(payload({success: false, reason: 'message from server'})));
  });
});
