import {describe, expect, test} from '@jest/globals';
import {mount} from '@vue/test-utils';
import {ApplicationModel} from 'layouts/ApplicationModel';
import {reactive} from 'vue';
import ApplicationTitle from 'layouts/ApplicationTitle.vue';
import {setupComponentTest} from 'app/test/utils/component';

setupComponentTest();

describe('ApplicationTitle', () => {
  test('should present users the application title', () => {
    const wrapper = mount(ApplicationTitle, {props: {model: reactive(new ApplicationModel())}});
    expect(wrapper.text()).toBe('瀚诚企业应用框架');
  });
});
