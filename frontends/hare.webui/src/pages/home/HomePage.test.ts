import { beforeEach, describe, expect, it } from '@jest/globals';
import { mount, VueWrapper } from '@vue/test-utils';
import { setupComponentTest } from 'app/test/utils/component';
import App from 'src/App.vue';
import { createApplicationRouter } from 'src/router';
import HomePage from 'pages/home/HomePage.vue';
import { Router } from 'vue-router';

setupComponentTest();

describe('HomePage', () => {
  let router: Router;
  let wrapper: VueWrapper;

  beforeEach(() => {
    router = createApplicationRouter();
    wrapper = mount(App, { global: { plugins: [router] } });
  });

  it('should be able to visited by router', async () => {
    await router.replace('/');
    await router.isReady();
    expect(wrapper.findComponent(HomePage).exists()).toBeTruthy();
  });
});
