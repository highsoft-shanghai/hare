import { describe, expect, test } from '@jest/globals';
import { mount } from '@vue/test-utils';
import { Application } from 'layouts/Application';
import { reactive } from 'vue';
import { setupComponentTest } from 'app/test/utils/component';
import PageHostControl from "layouts/PageHostControl.vue";
import { PageModel } from "commons/page/PageModel";
import { pagekey } from "commons/page/PageKey";

setupComponentTest();

describe('PageHostControl', () => {
  test('should present users page list', async () => {
    const wrapper = mount(PageHostControl, { props: { model: reactive(new Application(new PageModel(pagekey('router.home')))) } });
    expect(wrapper.findAll('.q-tab').length).toBe(1);
    expect(wrapper.findAll('.q-tab')[0].text()).toBe('首页');
  });
});
