import {describe, expect, it, beforeEach} from '@jest/globals';
import {mount, VueWrapper} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import App from 'src/App.vue';
import {createApplicationRouter} from 'src/router';
import {Router} from 'vue-router';
import ErrorNotFound from 'pages/ErrorNotFound.vue';

setupComponentTest();

describe('ErrorNotFound', () => {
  let router: Router;
  let wrapper: VueWrapper;

  beforeEach(() => {
    router = createApplicationRouter();
    wrapper = mount(App, {global: {plugins: [router]}});
  });

  it('should be visible if page not found', async () => {
    await router.replace('/any-not-found-page');
    await router.isReady();
    expect(wrapper.findComponent(ErrorNotFound).exists()).toBeTruthy();
  });
});
