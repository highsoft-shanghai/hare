import {describe, expect, it} from '@jest/globals';
import {Router} from 'vue-router';
import {VueRouterNavigator} from 'commons/router/VueRouterNavigator';
import {mock} from 'jest-mock-extended';

describe('VueRouterNavigator', () => {
  it('should replace current route to new one when goto succeeded', async () => {
    const router = mock<Router>();
    const navigator = new VueRouterNavigator(router);
    await navigator.goto('page.home');
    expect(router.replace).toBeCalledWith({name: 'page.home'});
  });
});
