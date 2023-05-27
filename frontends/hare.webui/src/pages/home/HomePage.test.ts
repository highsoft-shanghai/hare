import {beforeEach, describe, expect, it} from '@jest/globals';
import {mount, VueWrapper} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import App from 'src/App.vue';
import {installVueRouter} from 'src/router';
import HomePage from 'pages/home/HomePage.vue';
import {Router} from 'vue-router';
import {resettableGlobals} from 'commons/global/globals';
import {grantedAuthorities} from 'commons/context/GrantedAuthorities';

setupComponentTest();

describe('HomePage', () => {
  let router: Router;
  let wrapper: VueWrapper;

  beforeEach(() => {
    router = installVueRouter();
    wrapper = mount(App, {global: {plugins: [router]}});
    resettableGlobals.resetContext(grantedAuthorities());
  });

  it('should be able to visited by router', async () => {
    await router.replace('/');
    await router.isReady();
    expect(wrapper.findComponent(HomePage).exists()).toBeTruthy();
  });
});
