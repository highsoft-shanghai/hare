import {describe, expect, it} from '@jest/globals';
import {LoginModel} from 'pages/login/LoginModel';

describe('LoginModel', () => {
  it('should be un-submittable when both username and password are missing', () => {
    const model = new LoginModel();
    expect(model.submittable).toBeFalsy();
  });

  it('should be un-submittable when both username and password are present', () => {
    const model = new LoginModel();
    model.loginName.handleInput('john@highsoft.ltd');
    model.password.handleInput('simple-password');
    expect(model.submittable).toBeTruthy();
  });
});
