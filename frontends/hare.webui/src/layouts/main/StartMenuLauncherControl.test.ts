import {describe, expect, test} from '@jest/globals';
import {mount} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import StartMenuLauncherControl from 'layouts/main/StartMenuLauncherControl.vue';
import {QBtn} from 'quasar';
import {globals} from 'commons/global/globals';

setupComponentTest();

describe('StartMenuLauncherControl', () => {
  test('should present users entry button of the start menu', () => {
    const wrapper = mount(StartMenuLauncherControl, {props: {model: globals.application}});
    expect(wrapper.findComponent(QBtn).text()).toBe('menu');
  });

  test('should show start menu when user click it', () => {
    const model = globals.application;
    const wrapper = mount(StartMenuLauncherControl, {props: {model: model}});
    wrapper.trigger('click');
    expect(model.startMenu.visible).toBeTruthy();
  });
});
