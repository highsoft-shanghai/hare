import {beforeEach, describe, expect, it} from '@jest/globals';
import {mount, VueWrapper} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import App from 'src/App.vue';
import {installVueRouter} from 'src/router';
import {Router} from 'vue-router';
import ErrorNotFound from 'pages/ErrorNotFound.vue';
import MockAdapter from 'axios-mock-adapter';
import {api} from 'commons/api/api';
import {globals} from 'commons/global/globals';

setupComponentTest();

describe('ErrorNotFound', () => {
  let router: Router;
  let wrapper: VueWrapper;
  let mockApi: MockAdapter;

  beforeEach(async () => {
    mockApi = new MockAdapter(api);
    router = await installVueRouter();
    wrapper = mount(App, {global: {plugins: [router]}});
    mockApi.onGet('/api/access-tokens/current').reply(200, {grantedAuthorities: []});
    await globals.context.reset('access-token.test');
  });

  it('should be visible if page not found', async () => {
    await router.replace('/any-not-found-page');
    await router.isReady();
    expect(wrapper.findComponent(ErrorNotFound).exists()).toBeTruthy();
  });
});
