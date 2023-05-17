import { describe, expect, test } from '@jest/globals';
import { mount } from '@vue/test-utils';
import ApplicationHeaderControl from "layouts/main/ApplicationHeaderControl.vue";
import ApplicationTitleControl from "layouts/main/ApplicationTitleControl.vue";
import StartMenuControl from 'layouts/main/StartMenuControl.vue';
import ProfileMenuLauncherControl from 'layouts/main/ProfileMenuLauncherControl.vue';
import { setupComponentTest } from 'app/test/utils/component';
import { globals } from "commons/global/globals";

setupComponentTest();

describe('ApplicationHeaderControl', () => {
  test('should present users the main-menu button, the application-title and the user-profile-menu button', () => {
    const wrapper = mount(ApplicationHeaderControl, { props: { model: globals.application } });
    expect(wrapper.findComponent(StartMenuControl)).toBeDefined();
    expect(wrapper.findComponent(ApplicationTitleControl).text()).toBe('瀚诚企业应用框架');
    expect(wrapper.findComponent(ProfileMenuLauncherControl)).toBeDefined();
  });
});
