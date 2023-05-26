import {describe, expect, it} from '@jest/globals';
import {VueRouterNavigator} from 'commons/router/VueRouterNavigator';
import {Navigator} from 'commons/router/Navigator';
import {createVueRouter} from 'src/router';
import {setupComponentTest} from 'app/test/utils/component';

setupComponentTest();

describe('VueRouterNavigator', () => {
  it('should replace current route to new one when goto succeeded', async () => {
    const router = createVueRouter([{path: '/', name: 'page.home', component: async () => ({})}]);
    const navigator: Navigator = new VueRouterNavigator(router);
    await navigator.goto('page.home');
    expect(router.currentRoute.value.name).toBe('page.home');
  });

  it('should goto 404 page if page not found', async () => {
    const router = createVueRouter([{path: '/', name: 'page.home', component: async () => ({})}, {path: '/404', component: async () => ({})}]);
    const navigator: Navigator = new VueRouterNavigator(router);
    await navigator.goto('unknown-page');
    expect(router.currentRoute.value.path).toBe('/404');
  });
});
