import {describe, expect, test} from '@jest/globals';
import {DOMWrapper, flushPromises, mount} from '@vue/test-utils';
import {reactive} from 'vue';
import {setupComponentTest} from 'app/test/utils/component';
import StartMenuControl from 'layouts/main/StartMenuControl.vue';
import {globals} from 'commons/global/globals';
import StartMenuHeaderControl from 'layouts/main/StartMenuHeaderControl.vue';

setupComponentTest();

describe('StartMenuControl', () => {
  test('should be visible when model value is true', async () => {
    const model = reactive(globals.application);
    model.toggleStartMenu();
    mount(StartMenuControl, {props: {model: model}});
    const wrapper = new DOMWrapper(document.body);
    await flushPromises();
    expect(wrapper.find('.q-dialog').exists()).toBeTruthy();
    expect(wrapper.findComponent(StartMenuHeaderControl)).toBeDefined();
  });
});
