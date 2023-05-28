import {describe, expect, it} from '@jest/globals';
import {LoginModel} from 'pages/login/LoginModel';
import {mock} from 'jest-mock-extended';
import {Navigator} from 'commons/router/Navigator';
import {Context} from 'commons/context/Context';
import {LoginApi} from 'pages/login/LoginApi';

describe('LoginModel', () => {
  it('should be un-submittable when both username and password are missing', () => {
    const model = new LoginModel(mock<LoginApi>(), mock<Navigator>(), mock<Context>());
    expect(model.submittable).toBeFalsy();
  });

  it('should be un-submittable when both username and password are present', () => {
    const model = new LoginModel(mock<LoginApi>(), mock<Navigator>(), mock<Context>());
    model.loginName.handleInput('john@highsoft.ltd');
    model.password.handleInput('simple-password');
    expect(model.submittable).toBeTruthy();
  });
});
