import { installQuasarPlugin } from '@quasar/quasar-app-extension-testing-unit-jest';
import { afterAll, jest } from '@jest/globals';
import { Dialog, Notify } from 'quasar';
import { initializeGlobals } from "src/initialize";
import { mockDeep } from "jest-mock-extended";
import { App } from "vue";

export function setupComponentTest(): void {
  installQuasarPlugin({ plugins: { Dialog, Notify } });
  initializeGlobals(mockDeep<App>() as App<never>);
  window.scrollTo = jest.fn();

  afterAll(() => {
    jest.clearAllMocks();
  });
}
