import {describe, expect, test} from '@jest/globals';
import {mount} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import {StartMenu} from 'layouts/main/StartMenu';
import {reactive} from 'vue';
import StartMenuItemListControl from 'layouts/main/StartMenuItemListControl.vue';

setupComponentTest();

describe('StartMenuItemListControl', () => {
  test('should present users all the menu sub groups', () => {
    const wrapper = mount(StartMenuItemListControl, {props: {model: reactive(new StartMenu())}});
    expect(wrapper.findAll('.sub-group-header-control').map(x => x.text())).toStrictEqual(['人员信息维护', '人员信息分析']);
    expect(wrapper.findAll('.start-menu-item-label').map(x => x.text())).toStrictEqual(['新增人员', '检索人员', '人员状态']);
  });
});
