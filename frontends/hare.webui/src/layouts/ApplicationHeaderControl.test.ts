import {describe, expect, test} from '@jest/globals';
import {mount} from '@vue/test-utils';
import ApplicationHeaderBar from 'layouts/ApplicationHeaderBar.vue';
import {Application} from 'layouts/Application';
import {reactive} from 'vue';
import StartMenu from 'layouts/StartMenu.vue';
import ApplicationTitle from 'layouts/ApplicationTitle.vue';
import ProfileMenuButton from 'layouts/ProfileMenuButton.vue';
import {setupComponentTest} from 'app/test/utils/component';

setupComponentTest();

describe('ApplicationHeaderBar', () => {
  test('should present users the main-menu button, the application-title and the user-profile-menu button', () => {
    const wrapper = mount(ApplicationHeaderBar, {props: {model: reactive(new Application())}});
    expect(wrapper.findComponent(StartMenu)).toBeDefined();
    expect(wrapper.findComponent(ApplicationTitle).text()).toBe('瀚诚企业应用框架');
    expect(wrapper.findComponent(ProfileMenuButton)).toBeDefined();
  });
});
