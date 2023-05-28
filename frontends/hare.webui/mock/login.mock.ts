import {defineMock} from 'vite-plugin-mock-dev-server';

// noinspection JSUnusedGlobalSymbols
export default defineMock({
  url: '/api/logins',
  delay: 1000,
  body: {
    id: 'login.mock.id',
    accessToken: 'access-token.mock.id',
    success: true,
    message: ''
  }
});
