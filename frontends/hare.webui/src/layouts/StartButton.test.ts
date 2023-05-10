import {describe, expect, test} from '@jest/globals';
import {mount} from '@vue/test-utils';
import {reactive} from 'vue';
import {setupComponentTest} from 'app/test/utils/component';
import StartButton from 'layouts/StartButton.vue';
import {StartMenuModel} from 'layouts/StartMenuModel';
import {QBtn} from 'quasar';

setupComponentTest();

describe('StartButton', () => {
  test('should present users entry button of the start menu', () => {
    const wrapper = mount(StartButton, {props: {model: reactive(new StartMenuModel())}});
    expect(wrapper.findComponent(QBtn).text()).toBe('menu');
  });

  test('should show start menu when user click it', () => {
    const model = reactive(new StartMenuModel());
    const wrapper = mount(StartButton, {props: {model: model}});
    wrapper.trigger('click');
    expect(model.visible).toBeTruthy();
  });
});
