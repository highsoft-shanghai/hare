import {beforeEach, describe, expect, it} from '@jest/globals';
import {flushPromises, mount, VueWrapper} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import App from 'src/App.vue';
import {installVueRouter} from 'src/router';
import {Router} from 'vue-router';
import LoginPage from 'pages/login/LoginPage.vue';
import BlankLayoutControl from 'layouts/blank/BlankLayoutControl.vue';
import LoginNameControl from 'components/login/LoginNameControl.vue';
import PasswordControl from 'components/login/PasswordControl.vue';
import LoginSubmitButton from 'pages/login/LoginSubmitButton.vue';
import MockAdapter from 'axios-mock-adapter';
import {api} from 'commons/api/api';
import LoginResultControl from 'pages/login/LoginResultControl.vue';

setupComponentTest();

describe('LoginPage', () => {
  let router: Router;
  let wrapper: VueWrapper;
  let mockApi: MockAdapter;

  beforeEach(async () => {
    mockApi = new MockAdapter(api);
    router = installVueRouter();
    wrapper = mount(App, {global: {plugins: [router]}});
    await router.replace('/login');
    await router.isReady();
  });

  it('should be able to visited by router', async () => {
    expect(wrapper.findComponent(LoginPage).exists()).toBeTruthy();
  });

  it('should be under blank layout', async () => {
    expect(wrapper.findComponent(BlankLayoutControl).exists()).toBeTruthy();
  });

  it('should present users the login form', async () => {
    const loginPage = wrapper.findComponent(LoginPage);
    expect(loginPage.findComponent(LoginNameControl).find('.q-field__label').text()).toBe('用户名');
    expect(loginPage.findComponent(PasswordControl).find('.q-field__label').text()).toBe('密码');
  });

  it('should present disabled submit button if login form is not submittable', async () => {
    const loginPage = wrapper.findComponent(LoginPage);
    expect(loginPage.findComponent(LoginSubmitButton).find('button').element.disabled).toBeTruthy();
  });

  it('should present normal submit button if login form is submittable', async () => {
    const loginPage = wrapper.findComponent(LoginPage);
    await loginPage.findComponent(LoginNameControl).find('input').setValue('john@highsoft.ltd');
    await loginPage.findComponent(PasswordControl).find('input').setValue('simple-password');
    expect(loginPage.findComponent(LoginSubmitButton).find('button').element.disabled).toBeFalsy();
  });

  it('should redirect to home page when login success', async () => {
    mockApi.onPost('/api/logins').reply(201, {id: 'login.mock.id', success: true, accessToken: 'access-token.mock'});
    mockApi.onGet('/api/access-tokens/access-token.mock').reply(200, {grantedAuthorities: []});
    const loginPage = wrapper.findComponent(LoginPage);
    await loginPage.findComponent(LoginNameControl).find('input').setValue('john@highsoft.ltd');
    await loginPage.findComponent(PasswordControl).find('input').setValue('simple-password');
    await loginPage.findComponent(LoginSubmitButton).find('button').trigger('submit');
    await flushPromises();
    await router.isReady();
    expect(router.currentRoute.value.name).toBe('route.home');
  });

  it('should notice users when login failed', async () => {
    mockApi.onPost('/api/logins').reply(201, {id: 'login.mock.id', success: false, message: 'Failed to login in from test'});
    const loginPage = wrapper.findComponent(LoginPage);
    await loginPage.findComponent(LoginNameControl).find('input').setValue('john@highsoft.ltd');
    await loginPage.findComponent(PasswordControl).find('input').setValue('invalid-password');
    await loginPage.findComponent(LoginSubmitButton).find('button').trigger('submit');
    await flushPromises();
    expect(loginPage.findComponent(LoginResultControl).text()).toBe('Failed to login in from test');
  });
});
