import {beforeEach, describe, expect, it} from '@jest/globals';
import {VueRouterNavigator} from 'commons/router/VueRouterNavigator';
import {Navigator} from 'commons/router/Navigator';
import {createVueRouter} from 'src/router';
import {setupComponentTest} from 'app/test/utils/component';
import {Context} from 'commons/context/Context';
import {Authorities} from 'commons/context/Authorities';
import {any, Matcher, MatcherCreator, mock} from 'jest-mock-extended';
import {forbidden, redirectToLogin, success} from 'commons/context/AuthorizationResult';
import {requiredAuthorities, RequiredAuthorities} from 'commons/context/RequiredAuthorities';
import {isDeepStrictEqual} from 'util';

setupComponentTest();

export const equals: MatcherCreator<RequiredAuthorities> = (expectedValue) => {
  return new Matcher((actualValue) => (isDeepStrictEqual(expectedValue, actualValue)), 'RequiredAuthorities equals deeply');
};

describe('VueRouterNavigator', () => {
  const allowAnonymous = {meta: {requiredAuthorities: [Authorities.ANONYMOUS]}};
  const requireAuthenticated = {meta: {requiredAuthorities: [Authorities.AUTHENTICATED]}};
  const requireHome = {meta: {requiredAuthorities: ['home']}};
  const component = {component: async () => ({})};
  let context: ReturnType<typeof mock<Context>>;

  beforeEach(() => {
    context = mock<Context>();
  });

  it('should replace current route to new one when goto succeeded', async () => {
    const router = createVueRouter([{path: '/', name: 'home', ...component, ...allowAnonymous}]);
    context.authorize.calledWith(any()).mockReturnValue(success());
    const navigator: Navigator = new VueRouterNavigator(router, context);
    await navigator.goto('home');
    expect(router.currentRoute.value.name).toBe('home');
  });

  it('should navigate to 404 page if page is not found', async () => {
    const router = createVueRouter([{path: '/404', ...component, ...allowAnonymous}, {path: '/login', name: 'route.login', ...component, ...allowAnonymous}]);
    context.authorize.calledWith(any()).mockReturnValue(success());
    const navigator: Navigator = new VueRouterNavigator(router, context);
    await navigator.goto('unknown-page');
    expect(router.currentRoute.value.path).toBe('/404');
  });

  it('should navigate to login when current user is not authenticated and target failed to authorized', async () => {
    const router = createVueRouter([{path: '/', name: 'home', ...requireAuthenticated, ...component}, {path: '/login', name: 'route.login', ...component, ...allowAnonymous}]);
    context.authorize.calledWith(equals(requiredAuthorities(Authorities.ANONYMOUS))).mockReturnValue(success());
    context.authorize.calledWith(equals(requiredAuthorities(Authorities.AUTHENTICATED))).mockReturnValue(redirectToLogin());
    const navigator: Navigator = new VueRouterNavigator(router, context);
    await navigator.goto('home');
    expect(router.currentRoute.value.name).toBe('route.login');
  });

  it('should do nothing when current user is authenticated and target failed to authorized', async () => {
    const router = createVueRouter([{path: '/', name: 'test', ...allowAnonymous, ...component}, {path: '/home', name: 'home', ...requireHome, ...component}, {
      path: '/login',
      name: 'route.login', ...component, ...allowAnonymous
    }]);
    context.authorize.calledWith(any()).mockReturnValue(forbidden());
    const navigator: Navigator = new VueRouterNavigator(router, context);
    const lastRoute = router.currentRoute.value;
    await navigator.goto('home');
    expect(router.currentRoute.value).toBe(lastRoute);
  });

  it('should allow anonymous users to access routes which allow anonymous access', async () => {
    const router = createVueRouter([{path: '/', name: 'home', ...allowAnonymous, ...component}, {path: '/login', name: 'route.login', ...component, ...allowAnonymous}]);
    context.authorize.calledWith(any()).mockReturnValue(success());
    const navigator: Navigator = new VueRouterNavigator(router, context);
    await navigator.goto('home');
    expect(router.currentRoute.value.name).toBe('home');
  });
});
