import {defineMock, MockRequest} from 'vite-plugin-mock-dev-server';

// noinspection JSUnusedGlobalSymbols
export default defineMock([
  {
    url: '/api/access-tokens/current',
    delay: 500,
    body: ({headers}: MockRequest) => {
      switch (headers.get('Authorization')) {
        case 'Bearer access-token.mock.admin' : return {id: 'access-token.mock.admin', grantedAuthorities: ['admin']};
        case 'Bearer access-token.mock.user': return {id: 'access-token.mock.user', grantedAuthorities: []};
        default: return {};
      }
    }
  }
]);
