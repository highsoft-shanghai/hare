import {describe, expect, it} from '@jest/globals';
import {mount} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import {createVueRouter} from 'src/router';
import BlankLayoutControl from 'layouts/blank/BlankLayoutControl.vue';
import {RouterView} from 'vue-router';
import testRoutes from 'app/test/data/test-routes';

setupComponentTest();

describe('BlankLayoutControl', () => {
  it('should present users a blank frame', () => {
    const wrapper = mount(BlankLayoutControl, {global: {plugins: [createVueRouter(testRoutes)]}});
    expect(wrapper.text()).toBe('');
  });

  it('should present users a router view', () => {
    const wrapper = mount(BlankLayoutControl, {global: {plugins: [createVueRouter(testRoutes)]}});
    expect(wrapper.findComponent(RouterView).exists()).toBeTruthy();
  });
});
