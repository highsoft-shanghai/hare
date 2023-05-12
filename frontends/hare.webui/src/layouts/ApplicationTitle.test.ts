import { describe, expect, test } from '@jest/globals';
import { mount } from '@vue/test-utils';
import { Application } from 'layouts/Application';
import { reactive } from 'vue';
import { setupComponentTest } from 'app/test/utils/component';
import ApplicationTitleControl from "layouts/ApplicationTitleControl.vue";

setupComponentTest();

describe('ApplicationTitleControl', () => {
  test('should present users the application title', () => {
    const wrapper = mount(ApplicationTitleControl, { props: { model: reactive(new Application()) } });
    expect(wrapper.text()).toBe('瀚诚企业应用框架');
  });
});
