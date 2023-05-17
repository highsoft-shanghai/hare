import { describe, expect, test } from '@jest/globals';
import { mount } from '@vue/test-utils';
import { setupComponentTest } from 'app/test/utils/component';
import ApplicationTitleControl from "layouts/main/ApplicationTitleControl.vue";
import { globals } from "commons/global/globals";

setupComponentTest();

describe('ApplicationTitleControl', () => {
  test('should present users the application title', () => {
    const wrapper = mount(ApplicationTitleControl, { props: { model: globals.application } });
    expect(wrapper.text()).toBe('瀚诚企业应用框架');
  });
});
