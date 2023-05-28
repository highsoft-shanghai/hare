import {describe, expect, test} from '@jest/globals';
import {mount} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import {LoginNameModel} from 'components/login/LoginNameModel';
import LoginNameControl from 'components/login/LoginNameControl.vue';
import {reactive} from 'vue';

setupComponentTest();

describe('LoginNameControl', () => {
  test('should present users the an input box, a label and an icon', () => {
    const wrapper = mount(LoginNameControl, {props: {model: reactive(new LoginNameModel())}});
    expect(wrapper.find('input').exists()).toBeTruthy();
    expect(wrapper.find('.q-field__label').text()).toBe('用户名');
    expect(wrapper.find('.q-icon').text()).toBe('perm_identity');
  });
});
