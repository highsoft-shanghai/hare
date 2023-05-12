import { describe, expect, test } from '@jest/globals';
import { mount } from '@vue/test-utils';
import { setupComponentTest } from 'app/test/utils/component';
import PageHostControl from "layouts/PageHostControl.vue";
import { globals } from "commons/global/globals";

setupComponentTest();

describe('PageHostControl', () => {
  test('should present users page list', async () => {
    const wrapper = mount(PageHostControl, { props: { model: globals.application } });
    expect(wrapper.findAll('.q-tab').length).toBe(1);
    expect(wrapper.findAll('.q-tab')[0].text()).toBe('首页');
  });
});
