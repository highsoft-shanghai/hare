import {describe, expect, test} from '@jest/globals';
import {mount} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import {globals} from 'commons/global/globals';
import StartMenuGroupListControl from 'layouts/StartMenuGroupListControl.vue';

setupComponentTest();

describe('StartMenuGroupListControl', () => {
  test('should present users all the menu groups', () => {
    const wrapper = mount(StartMenuGroupListControl, {props: {model: globals.application}});
    expect(wrapper.findAll('.q-tab__label').map(x => x.text())).toStrictEqual(['人员管理', '客户管理', '商品管理', '库存管理', '供应商管理', '学习计划管理']);
  });
});
