import {beforeEach, describe, expect, it} from '@jest/globals';
import {flushPromises, mount} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import {TextInputModel} from 'components/base/TextInputModel';
import {reactive, UnwrapRef} from 'vue';
import {message} from 'commons/i18n/i18n';
import TextInputControl from 'components/base/TextInputControl.vue';

setupComponentTest();

describe('TextInputControl', () => {
  let model: UnwrapRef<TextInputModel>;

  beforeEach(() => {
    model = reactive(new TextInputModel(message('Label Content')));
  });

  it('should present users a text input and a label', async () => {
    const wrapper = mount(TextInputControl, {props: {model: model}});
    expect(wrapper.find('input').exists()).toBeTruthy();
    expect(wrapper.find('.q-field__label').text()).toBe('Label Content');
  });

  it('should be able to configured without label', async () => {
    const wrapper = mount(TextInputControl, {props: {model: reactive(new TextInputModel())}});
    expect(wrapper.find('input').exists()).toBeTruthy();
    expect(wrapper.find('.q-field__label').exists()).toBeFalsy();
  });

  it('should be able to present values from model', async () => {
    const wrapper = mount(TextInputControl, {props: {model: model}});
    model.handleInput('abc');
    await flushPromises();
    expect(wrapper.find('input').element.value).toBe('abc');
  });

  it('should update model when user input texts', async () => {
    const wrapper = mount(TextInputControl, {props: {model: model}});
    await wrapper.find('input').setValue('input text');
    expect(model.value).toBe('input text');
  });

  it('should be able to support prepend slot', async () => {
    const wrapper = mount(TextInputControl, {props: {model: model}, slots: {prepend: 'Prepend Content'}});
    expect(wrapper.find('.q-field__prepend').text()).toBe('Prepend Content');
  });
});
