import {beforeEach, describe, expect, it} from '@jest/globals';
import {mount, VueWrapper} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import App from 'src/App.vue';
import {installVueRouter} from 'src/router';
import HomePage from 'pages/home/HomePage.vue';
import {Router} from 'vue-router';
import {globals} from 'commons/global/globals';
import MockAdapter from 'axios-mock-adapter';
import {api} from 'commons/api/api';

setupComponentTest();

describe('HomePage', () => {
  let router: Router;
  let wrapper: VueWrapper;
  let mockApi: MockAdapter;

  beforeEach(async () => {
    mockApi = new MockAdapter(api);
    router = await installVueRouter();
    wrapper = mount(App, {global: {plugins: [router]}});
    mockApi.onGet('/api/access-tokens/current').reply(200, {authorities: []});
    await globals.context.reset('access-token.test');
  });

  it('should be able to visited by router', async () => {
    await router.replace('/');
    await router.isReady();
    expect(wrapper.findComponent(HomePage).exists()).toBeTruthy();
  });
});
