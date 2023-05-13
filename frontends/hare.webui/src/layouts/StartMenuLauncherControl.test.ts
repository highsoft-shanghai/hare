import { describe, expect, test } from '@jest/globals';
import { mount } from '@vue/test-utils';
import { reactive } from 'vue';
import { setupComponentTest } from 'app/test/utils/component';
import StartMenuLauncherControl from "layouts/StartMenuLauncherControl.vue";
import { StartMenu } from 'layouts/StartMenu';
import { QBtn } from 'quasar';

setupComponentTest();

describe('StartButton', () => {
  test('should present users entry button of the start menu', () => {
    const wrapper = mount(StartMenuLauncherControl, { props: { model: reactive(new StartMenu()) } });
    expect(wrapper.findComponent(QBtn).text()).toBe('menu');
  });

  test('should show start menu when user click it', () => {
    const model = reactive(new StartMenu());
    const wrapper = mount(StartMenuLauncherControl, { props: { model: model } });
    wrapper.trigger('click');
    expect(model.visible).toBeTruthy();
  });
});
