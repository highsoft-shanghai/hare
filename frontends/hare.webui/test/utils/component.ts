import {installQuasarPlugin} from '@quasar/quasar-app-extension-testing-unit-jest';
import {afterAll, beforeEach, jest} from '@jest/globals';
import {Dialog, Notify} from 'quasar';
import {initializeGlobals} from 'src/initialize';
import {mockDeep} from 'jest-mock-extended';
import {App} from 'vue';
import {installGlobalCulture} from 'commons/i18n/VueCulture';

export function setupComponentTest(): void {
  installQuasarPlugin({plugins: {Dialog, Notify}});
  window.scrollTo = jest.fn();

  beforeEach(() => {
    (Notify as unknown as Record<string, unknown>)['create'] = jest.fn();
    installGlobalCulture();
    initializeGlobals(mockDeep<App>() as App<never>);
  });

  afterAll(() => {
    jest.clearAllMocks();
  });
}
