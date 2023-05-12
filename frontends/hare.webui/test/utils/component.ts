import {installQuasarPlugin} from '@quasar/quasar-app-extension-testing-unit-jest';
import {afterAll, beforeAll, jest} from '@jest/globals';
import {Translator} from 'commons/i18n/Translator';
import {Dialog, Notify} from 'quasar';
import { initializeGlobals } from "src/initialize";

export function setupComponentTest(): void {
  installQuasarPlugin({plugins: {Dialog, Notify}});
  initializeGlobals();
  window.scrollTo = jest.fn();

  beforeAll(() => {
    Translator.initialize();
  });

  afterAll(() => {
    jest.clearAllMocks();
  });
}
