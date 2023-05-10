import {describe, expect, test} from '@jest/globals';
import {mount} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import MainLayout from 'layouts/MainLayout.vue';
import ApplicationHeaderBar from 'layouts/ApplicationHeaderBar.vue';
import {createApplicationRouter} from 'src/router';
import ApplicationTabBar from 'layouts/ApplicationTabBar.vue';
import {RouterView} from 'vue-router';

setupComponentTest();

describe('MainLayout', () => {
  test('should present users the application frame', () => {
    const wrapper = mount(MainLayout, {global: {plugins: [createApplicationRouter()]}});
    expect(wrapper.findComponent(ApplicationHeaderBar)).toBeDefined();
    expect(wrapper.findComponent(ApplicationTabBar)).toBeDefined();
    expect(wrapper.findComponent(RouterView)).toBeDefined();
  });
});
