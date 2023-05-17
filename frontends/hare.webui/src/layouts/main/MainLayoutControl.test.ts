import { describe, expect, test } from '@jest/globals';
import { mount } from '@vue/test-utils';
import { setupComponentTest } from 'app/test/utils/component';
import MainLayoutControl from 'layouts/main/MainLayoutControl.vue';
import { createApplicationRouter } from 'src/router';
import { RouterView } from 'vue-router';
import ApplicationHeaderControl from "layouts/main/ApplicationHeaderControl.vue";
import PageHostControl from "layouts/main/PageHostControl.vue";

setupComponentTest();

describe('MainLayoutControl', () => {
  test('should present users the application frame', () => {
    const wrapper = mount(MainLayoutControl, { global: { plugins: [createApplicationRouter()] } });
    expect(wrapper.findComponent(ApplicationHeaderControl)).toBeDefined();
    expect(wrapper.findComponent(PageHostControl)).toBeDefined();
    expect(wrapper.findComponent(RouterView)).toBeDefined();
  });
});
