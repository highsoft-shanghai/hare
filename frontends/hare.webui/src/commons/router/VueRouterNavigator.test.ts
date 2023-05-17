import {describe, expect, it} from '@jest/globals';
import {Router} from 'vue-router';
import {VueRouterNavigator} from 'commons/router/VueRouterNavigator';
import {mock} from 'jest-mock-extended';
import {Navigator} from 'commons/router/Navigator';

describe('VueRouterNavigator', () => {
  it('should replace current route to new one when goto succeeded', async () => {
    const router = mock<Router>();
    const navigator: Navigator = new VueRouterNavigator(router);
    router.hasRoute.calledWith('page.home').mockReturnValue(true);
    await navigator.goto('page.home');
    expect(router.replace).toBeCalledWith({name: 'page.home'});
  });

  it('should goto 404 page if page not found', async () => {
    const router = mock<Router>();
    const navigator: Navigator = new VueRouterNavigator(router);
    router.hasRoute.calledWith('unknown-page').mockReturnValue(false);
    await navigator.goto('unknown-page');
    expect(router.replace).toBeCalledWith('/404');
  });
});
