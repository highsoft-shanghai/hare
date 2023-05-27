import {describe, expect, it} from '@jest/globals';
import {VueRouterNavigator} from 'commons/router/VueRouterNavigator';
import {Navigator} from 'commons/router/Navigator';
import {createVueRouter} from 'src/router';
import {setupComponentTest} from 'app/test/utils/component';
import {Context} from 'commons/context/Context';
import {grantedAuthorities} from 'commons/context/GrantedAuthorities';
import {Authorities} from 'commons/context/Authorities';

setupComponentTest();

describe('VueRouterNavigator', () => {
  const allowAnonymous = {meta: {requiredAuthorities: [Authorities.ANONYMOUS]}};
  const requireAuthenticated = {meta: {requiredAuthorities: [Authorities.AUTHENTICATED]}};
  const requireHome = {meta: {requiredAuthorities: ['home']}};
  const component = {component: async () => ({})};
  const context = new Context();

  it('should replace current route to new one when goto succeeded', async () => {
    const router = createVueRouter([{path: '/', name: 'home', ...component, ...allowAnonymous}]);
    const navigator: Navigator = new VueRouterNavigator(router, context);
    await navigator.goto('home');
    expect(router.currentRoute.value.name).toBe('home');
  });

  it('should navigate to 404 page if page is not found', async () => {
    const router = createVueRouter([{path: '/404', ...component, ...allowAnonymous}, {path: '/login', name: 'route.login', ...component, ...allowAnonymous}]);
    const navigator: Navigator = new VueRouterNavigator(router, context);
    await navigator.goto('unknown-page');
    expect(router.currentRoute.value.path).toBe('/404');
  });

  it('should navigate to login when current user is not authenticated and target failed to authorized', async () => {
    const router = createVueRouter([{path: '/', name: 'home', ...requireAuthenticated, ...component}, {path: '/login', name: 'route.login', ...component, ...allowAnonymous}]);
    const navigator: Navigator = new VueRouterNavigator(router, context);
    await navigator.goto('home');
    expect(router.currentRoute.value.name).toBe('route.login');
  });

  it('should do nothing when current user is authenticated and target failed to authorized', async () => {
    const router = createVueRouter([{path: '/', name: 'test', ...allowAnonymous, ...component}, {path: '/home', name: 'home', ...requireHome, ...component}, {path: '/login', name: 'route.login', ...component, ...allowAnonymous}]);
    context.reset(grantedAuthorities());
    const navigator: Navigator = new VueRouterNavigator(router, context);
    const lastRoute = router.currentRoute.value;
    await navigator.goto('home');
    expect(router.currentRoute.value).toBe(lastRoute);
  });

  it('should allow anonymous users to access routes which allow anonymous access', async () => {
    const router = createVueRouter([{path: '/', name: 'home', ...allowAnonymous, ...component}, {path: '/login', name: 'route.login', ...component, ...allowAnonymous}]);
    const navigator: Navigator = new VueRouterNavigator(router, context);
    await navigator.goto('home');
    expect(router.currentRoute.value.name).toBe('home');
  });
});
