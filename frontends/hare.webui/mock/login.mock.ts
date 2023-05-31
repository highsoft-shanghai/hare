import {defineMock, MockRequest} from 'vite-plugin-mock-dev-server';

// noinspection JSUnusedGlobalSymbols
export default defineMock({
  url: '/api/logins',
  method: 'POST',
  delay: 1000,
  status: 201,
  body: ({body}: MockRequest) => {
    switch (body['username']) {
      case 'admin':return {id: 'login.mock.admin', accessToken: 'access-token.mock.admin', success: true};
      case 'user':return {id: 'login.mock.user', accessToken: 'access-token.mock.user', success: true};
      default: return {id: 'login.mock.failed', success: false, reason: 'Username and password not match from mock'};
    }
  }
});
