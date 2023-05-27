import {describe, expect, it} from '@jest/globals';
import {VueRouterNavigator} from 'commons/router/VueRouterNavigator';
import {Navigator} from 'commons/router/Navigator';
import {createVueRouter} from 'src/router';
import {setupComponentTest} from 'app/test/utils/component';
import {Authorizer} from 'commons/security/Authorizer';
import {GrantedAuthorities} from 'commons/security/GrantedAuthorities';
import {Authorities} from 'commons/security/Authorities';

setupComponentTest();

describe('VueRouterNavigator', () => {
  const allowAnonymous = {meta: {requiredAuthorities: [Authorities.ANONYMOUS]}};
  const component = {component: async () => ({})};
  const authorizer = new Authorizer(GrantedAuthorities.ANONYMOUS);

  it('should replace current route to new one when goto succeeded', async () => {
    const router = createVueRouter([{path: '/', name: 'home', ...component, ...allowAnonymous}]);
    const navigator: Navigator = new VueRouterNavigator(router, authorizer);
    await navigator.goto('home');
    expect(router.currentRoute.value.name).toBe('home');
  });

  it('should navigate to 404 page if page is not found', async () => {
    const router = createVueRouter([{path: '/404', ...component, ...allowAnonymous}, {path: '/login', name: 'route.login', ...component, ...allowAnonymous}]);
    const navigator: Navigator = new VueRouterNavigator(router, authorizer);
    await navigator.goto('unknown-page');
    expect(router.currentRoute.value.path).toBe('/404');
  });

  it('should navigate to login when target is not authorized', async () => {
    const router = createVueRouter([{path: '/', name: 'home', ...component}, {path: '/login', name: 'route.login', ...component, ...allowAnonymous}]);
    const navigator: Navigator = new VueRouterNavigator(router, authorizer);
    await navigator.goto('home');
    expect(router.currentRoute.value.name).toBe('route.login');
  });

  it('should allow anonymous users to access routes allow anonymous access', async () => {
    const router = createVueRouter([{path: '/', name: 'home', ...allowAnonymous, ...component}, {path: '/login', name: 'route.login', ...component, ...allowAnonymous}]);
    const navigator: Navigator = new VueRouterNavigator(router, authorizer);
    await navigator.goto('home');
    expect(router.currentRoute.value.name).toBe('home');
  });
});
