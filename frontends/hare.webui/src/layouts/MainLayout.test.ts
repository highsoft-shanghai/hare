import { describe, expect, test } from '@jest/globals';
import { mount } from '@vue/test-utils';
import { setupComponentTest } from 'app/test/utils/component';
import MainLayout from 'layouts/MainLayout.vue';
import { createApplicationRouter } from 'src/router';
import { RouterView } from 'vue-router';
import ApplicationHeaderControl from "layouts/ApplicationHeaderControl.vue";
import PageHostControl from "layouts/PageHostControl.vue";

setupComponentTest();

describe('MainLayout', () => {
  test('should present users the application frame', () => {
    const wrapper = mount(MainLayout, { global: { plugins: [createApplicationRouter()] } });
    expect(wrapper.findComponent(ApplicationHeaderControl)).toBeDefined();
    expect(wrapper.findComponent(PageHostControl)).toBeDefined();
    expect(wrapper.findComponent(RouterView)).toBeDefined();
  });
});
