import {beforeEach, describe, expect, it} from '@jest/globals';
import {flushPromises, mount} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import {TextInputModel} from 'components/base/TextInputModel';
import LoginNameControl from 'components/login/LoginNameControl.vue';
import {ref} from 'vue';
import {i18n} from 'commons/i18n/i18n';

setupComponentTest();

describe('LoginNameControl', () => {
  const model = ref(new TextInputModel(i18n('label.login-name')));

  beforeEach(() => {
    model.value = new TextInputModel(i18n('label.login-name'));
  });

  it('should present users the an input box, a label and an icon', () => {
    const wrapper = mount(LoginNameControl, {props: {model: model.value}});
    expect(wrapper.find('input').exists()).toBeTruthy();
    expect(wrapper.find('.q-field__label').text()).toBe('用户名');
    expect(wrapper.find('.q-icon').text()).toBe('perm_identity');
  });

  it('should be able present values from model', async () => {
    const wrapper = mount(LoginNameControl, {props: {model: model.value}});
    model.value.handleInput('abc');
    await flushPromises();
    expect(wrapper.find('input').element.value).toBe('abc');
  });

  it('should update model when user input texts', async () => {
    const wrapper = mount(LoginNameControl, {props: {model: model.value}});
    await wrapper.find('input').setValue('input text');
    expect(model.value.value).toBe('input text');
  });
});
