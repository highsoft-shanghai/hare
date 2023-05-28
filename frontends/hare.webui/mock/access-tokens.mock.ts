import {defineMock} from 'vite-plugin-mock-dev-server';

// noinspection JSUnusedGlobalSymbols
export default defineMock({
  url: '/api/access-tokens/access-token.mock.id',
  delay: 500,
  body: {
    id: 'access-token.mock.id',
    grantedAuthorities: []
  }
});
