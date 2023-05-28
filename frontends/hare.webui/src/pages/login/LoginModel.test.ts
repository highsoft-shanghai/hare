import {beforeEach, describe, expect, it} from '@jest/globals';
import {LoginModel} from 'pages/login/LoginModel';
import {mock} from 'jest-mock-extended';
import {Navigator} from 'commons/router/Navigator';
import {Context} from 'commons/context/Context';
import {LoginApi} from 'pages/login/LoginApi';

describe('LoginModel', () => {
  let mockApi: LoginApi, mockNavigator: Navigator, mockContext: Context, model: LoginModel;

  beforeEach(() => {
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
});
