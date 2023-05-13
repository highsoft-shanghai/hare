import { describe, expect, test } from '@jest/globals';
import { DOMWrapper, flushPromises, mount } from '@vue/test-utils';
import { reactive } from 'vue';
import { setupComponentTest } from 'app/test/utils/component';
import { StartMenu } from 'layouts/StartMenu';
import StartMenuControl from 'layouts/StartMenuControl.vue';
import { QPage } from 'quasar';

setupComponentTest();

describe('StartMenu', () => {
  test('should be visible when model value is true', async () => {
    const model = reactive(new StartMenu());
    model.toggleVisible();
    mount(StartMenuControl, { props: { model: model } });
    const wrapper = new DOMWrapper(document.body);
    await flushPromises();
    expect(wrapper.find('.q-dialog').exists()).toBeTruthy();
    expect(wrapper.find('.q-dialog').find('h6').text()).toBe('开始');
    expect(wrapper.find('.q-dialog').findComponent(QPage).text()).toBe('此功能正在建设中，敬请期待……');
  });
});
