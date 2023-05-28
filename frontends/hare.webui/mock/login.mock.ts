import {defineMock, MockRequest} from 'vite-plugin-mock-dev-server';

// noinspection JSUnusedGlobalSymbols
export default defineMock({
  url: '/api/logins',
  method: 'POST',
  delay: 1000,
  status: 201,
  body: ({body}: MockRequest) => {
    switch(body['loginName']) {
      case 'admin': return {id: 'login.mock.admin', accessToken: 'access-token.mock.admin', success: true, message: ''};
      case 'user': return {id: 'login.mock.user', accessToken: 'access-token.mock.user', success: true, message: ''};
      default: return {id: 'login.mock.failed', success: false, message: 'Username and password not match from mock'};
    }
  }
});
