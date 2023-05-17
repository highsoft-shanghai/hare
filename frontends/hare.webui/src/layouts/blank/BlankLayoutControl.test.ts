import {describe, expect, test} from '@jest/globals';
import {mount} from '@vue/test-utils';
import {setupComponentTest} from 'app/test/utils/component';
import {createApplicationRouter} from 'src/router';
import BlankLayoutControl from 'layouts/blank/BlankLayoutControl.vue';

setupComponentTest();

describe('BlankLayoutControl', () => {
  test('should present users a blank frame', () => {
    const wrapper = mount(BlankLayoutControl, {global: {plugins: [createApplicationRouter()]}});
    expect(wrapper.text()).toBe('');
  });
});
