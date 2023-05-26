import {beforeEach, describe, expect, it} from '@jest/globals';
import {mount, VueWrapper} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import App from 'src/App.vue';
import {installVueRouter} from 'src/router';
import {Router} from 'vue-router';
import LoginPage from 'pages/login/LoginPage.vue';
import BlankLayoutControl from 'layouts/blank/BlankLayoutControl.vue';

setupComponentTest();

describe('LoginPage', () => {
  let router: Router;
  let wrapper: VueWrapper;

  beforeEach(() => {
    router = installVueRouter();
    wrapper = mount(App, {global: {plugins: [router]}});
  });

  it('should be able to visited by router', async () => {
    await router.replace('/login');
    await router.isReady();
    expect(wrapper.findComponent(LoginPage).exists()).toBeTruthy();
  });

  it('should be under blank layout', async () => {
    await router.replace('/login');
    await router.isReady();
    expect(wrapper.findComponent(BlankLayoutControl).exists()).toBeTruthy();
  });
});
