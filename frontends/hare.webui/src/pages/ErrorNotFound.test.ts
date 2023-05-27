import {beforeEach, describe, expect, it} from '@jest/globals';
import {mount, VueWrapper} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import App from 'src/App.vue';
import {installVueRouter} from 'src/router';
import {Router} from 'vue-router';
import ErrorNotFound from 'pages/ErrorNotFound.vue';
import {resettableGlobals} from 'commons/global/globals';
import {grantedAuthorities} from 'commons/context/GrantedAuthorities';

setupComponentTest();

describe('ErrorNotFound', () => {
  let router: Router;
  let wrapper: VueWrapper;

  beforeEach(() => {
    router = installVueRouter();
    wrapper = mount(App, {global: {plugins: [router]}});
    resettableGlobals.resetContext(grantedAuthorities());
  });

  it('should be visible if page not found', async () => {
    await router.replace('/any-not-found-page');
    await router.isReady();
    expect(wrapper.findComponent(ErrorNotFound).exists()).toBeTruthy();
  });
});
