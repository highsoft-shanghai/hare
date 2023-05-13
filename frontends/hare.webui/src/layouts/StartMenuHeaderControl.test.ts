import {describe, expect, test} from '@jest/globals';
import {mount} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import {globals} from 'commons/global/globals';
import StartMenuHeaderControl from 'layouts/StartMenuHeaderControl.vue';
import {QToolbarTitle} from 'quasar';

setupComponentTest();

describe('StartMenuHeaderControl', () => {
  test('should present users correct start menu header info', () => {
    const wrapper = mount(StartMenuHeaderControl, {props: {model: globals.application}});
    expect(wrapper.find('.start-menu-back-action-control')).toBeDefined();
    expect(wrapper.findComponent(QToolbarTitle).text()).toBe('瀚诚企业应用框架');
  });
});
