import {beforeEach, describe, expect, it} from '@jest/globals';
import {LoginModel} from 'pages/login/LoginModel';
import {any, mock} from 'jest-mock-extended';
import {Navigator} from 'commons/router/Navigator';
import {Context} from 'commons/context/Context';
import {LoginApi} from 'pages/login/LoginApi';
import {initializePayloadFactories} from 'commons/payload/FactoriesInitializer';
import {payload} from 'commons/payload/Payload';
import {authenticationResult} from 'commons/context/AuthenticationResult';

describe('LoginModel', () => {
  let mockApi: ReturnType<typeof mock<LoginApi>>;
  let mockNavigator: ReturnType<typeof mock<Navigator>>;
  let mockContext: ReturnType<typeof mock<Context>>;
  let model: LoginModel;

  beforeEach(() => {
    initializePayloadFactories();
    mockApi = mock<LoginApi>();
    mockNavigator = mock<Navigator>();
    mockContext = mock<Context>();
    model = new LoginModel(mockApi, mockNavigator, mockContext);
  });

  it('should be un-submittable when both username and password are missing', () => {
    expect(model.submittable).toBeFalsy();
  });

  it('should be un-submittable when both username and password are present', () => {
    model.loginName.handleInput('john@highsoft.ltd');
    model.password.handleInput('simple-password');
    expect(model.submittable).toBeTruthy();
  });

  it('should redirect to home page if submit success', async () => {
    mockApi.login.calledWith(any()).mockReturnValue(payload({success: true, accessToken: 'access-token.mock'}));
    model.loginName.handleInput('john@highsoft.ltd');
    model.password.handleInput('simple-password');
    await model.submit();
    expect(mockContext.reset).toBeCalledWith('access-token.mock');
    expect(mockNavigator.goto).toBeCalledWith('route.home');
  });

  it('should report error when submit failed', async () => {
    mockApi.login.calledWith(any()).mockReturnValue(payload({success: false, reason: 'message from server'}));
    model.loginName.handleInput('john@highsoft.ltd');
    model.password.handleInput('simple-password');
    await model.submit();
    expect(mockContext.reset).not.toBeCalled();
    expect(mockNavigator.goto).not.toBeCalled();
    expect(model.lastAuthenticationResult).toEqual(authenticationResult(payload({success: false, reason: 'message from server'})));
  });
});
