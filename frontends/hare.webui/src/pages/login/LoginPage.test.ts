import {beforeEach, describe, expect, it} from '@jest/globals';
import {mount, VueWrapper} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import App from 'src/App.vue';
import {installVueRouter} from 'src/router';
import {Router} from 'vue-router';
import LoginPage from 'pages/login/LoginPage.vue';
import BlankLayoutControl from 'layouts/blank/BlankLayoutControl.vue';
import LoginNameControl from 'components/login/LoginNameControl.vue';
import PasswordControl from 'components/login/PasswordControl.vue';
import {QBtn} from 'quasar';

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

  it('should present users the login form', async () => {
    await router.replace('/login');
    await router.isReady();
    const loginPage = wrapper.findComponent(LoginPage);
    expect(loginPage.findComponent(LoginNameControl).find('.q-field__label').text()).toBe('用户名');
    expect(loginPage.findComponent(PasswordControl).find('.q-field__label').text()).toBe('密码');
  });

  it('should present disabled submit button if login form is not submittable', async () => {
    await router.replace('/login');
    await router.isReady();
    const loginPage = wrapper.findComponent(LoginPage);
    expect(loginPage.findComponent(QBtn).vm.disable).toBeTruthy();
  });
});
