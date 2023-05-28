import {defineMock} from 'vite-plugin-mock-dev-server';

// noinspection JSUnusedGlobalSymbols
export default defineMock([
  {
    url: '/api/access-tokens/access-token.mock.admin',
    delay: 500,
    body: {
      id: 'access-token.mock.admin',
      grantedAuthorities: ['admin']
    }
  },
  {
    url: '/api/access-tokens/access-token.mock.user',
    delay: 500,
    body: {
      id: 'access-token.mock.user',
      grantedAuthorities: []
    }
  }
]);
