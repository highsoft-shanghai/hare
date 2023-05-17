import {describe, expect, test} from '@jest/globals';
import {mount} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import StartMenuGroupListControl from 'layouts/main/StartMenuGroupListControl.vue';
import {StartMenu} from 'layouts/main/StartMenu';
import {reactive} from 'vue';
import {QTab} from 'quasar';

setupComponentTest();

describe('StartMenuGroupListControl', () => {
  test('should present users all the menu groups', () => {
    const wrapper = mount(StartMenuGroupListControl, {props: {model: reactive(new StartMenu())}});
    expect(wrapper.findAll('.q-tab__label').map(x => x.text())).toStrictEqual(['人员管理', '客户管理', '商品管理', '库存管理', '供应商管理', '学习计划管理']);
  });

  test('should be able to activate groups', () => {
    const model = reactive(new StartMenu());
    const wrapper = mount(StartMenuGroupListControl, {props: {model: model}});
    wrapper.findAllComponents(QTab)[2].trigger('click');
    expect(model.activeGroupKey).toBe('products');
  });
});
