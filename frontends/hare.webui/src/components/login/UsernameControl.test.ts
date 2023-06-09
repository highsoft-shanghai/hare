import {beforeEach, describe, expect, it} from '@jest/globals';
import {flushPromises, mount} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import {TextInputModel} from 'components/base/TextInputModel';
import {reactive, UnwrapRef} from 'vue';
import {message} from 'commons/i18n/i18n';
import {Username} from 'components/login/Username';
import UsernameControl from 'components/login/UsernameControl.vue';

setupComponentTest();

describe('UsernameControl', () => {
  let model: UnwrapRef<TextInputModel>;

  beforeEach(() => {
    model = reactive(new TextInputModel(message('Label Content'), new Username()));
  });

  it('should present users the an input box, a label and an icon', () => {
    const wrapper = mount(UsernameControl, {props: {model: model}});
    expect(wrapper.find('input').exists()).toBeTruthy();
    expect(wrapper.find('.q-field__label').text()).toBe('Label Content');
    expect(wrapper.find('.q-icon').text()).toBe('perm_identity');
  });

  it('should link model with underlying text input', async () => {
    const wrapper = mount(UsernameControl, {props: {model: model}});
    model.handleInput('abc');
    await flushPromises();
    expect(wrapper.find('input').element.value).toBe('abc');
  });
});
