import { describe, expect, test } from '@jest/globals';
import { mount } from '@vue/test-utils';
import { Application } from 'layouts/Application';
import { reactive } from 'vue';
import ApplicationHeaderControl from "layouts/ApplicationHeaderControl.vue";
import ApplicationTitleControl from "layouts/ApplicationTitleControl.vue";
import StartMenu from 'layouts/StartMenu.vue';
import ProfileMenuLauncherControl from 'layouts/ProfileMenuLauncherControl.vue';
import { setupComponentTest } from 'app/test/utils/component';

setupComponentTest();

describe('ApplicationHeaderControl', () => {
  test('should present users the main-menu button, the application-title and the user-profile-menu button', () => {
    const wrapper = mount(ApplicationHeaderControl, { props: { model: reactive(new Application()) } });
    expect(wrapper.findComponent(StartMenu)).toBeDefined();
    expect(wrapper.findComponent(ApplicationTitleControl).text()).toBe('瀚诚企业应用框架');
    expect(wrapper.findComponent(ProfileMenuLauncherControl)).toBeDefined();
  });
});
