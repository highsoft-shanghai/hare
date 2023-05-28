import {beforeEach, describe, expect, it} from '@jest/globals';
import {flushPromises, mount} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import {TextInputModel} from 'components/base/TextInputModel';
import LoginNameControl from 'components/login/LoginNameControl.vue';
import {reactive, UnwrapRef} from 'vue';
import {message} from 'commons/i18n/i18n';

setupComponentTest();

describe('LoginNameControl', () => {
  let model: UnwrapRef<TextInputModel>;

  beforeEach(() => {
    model = reactive(new TextInputModel(message('Label Content')));
  });

  it('should present users the an input box, a label and an icon', () => {
    const wrapper = mount(LoginNameControl, {props: {model: model}});
    expect(wrapper.find('input').exists()).toBeTruthy();
    expect(wrapper.find('.q-field__label').text()).toBe('Label Content');
    expect(wrapper.find('.q-icon').text()).toBe('perm_identity');
  });

  it('should link model with underlying text input', async () => {
    const wrapper = mount(LoginNameControl, {props: {model: model}});
    model.handleInput('abc');
    await flushPromises();
    expect(wrapper.find('input').element.value).toBe('abc');
  });
});
