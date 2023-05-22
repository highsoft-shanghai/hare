import {defineMock} from 'vite-plugin-mock-dev-server';

// noinspection JSUnusedGlobalSymbols
export default defineMock({
  url: '/api/logins',
  body: {
    id: 'mock-login-id',
    accessToken: 'mock-access-token',
    success: true,
    message: ''
  }
});
