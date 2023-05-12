import { installQuasarPlugin } from '@quasar/quasar-app-extension-testing-unit-jest';
import { afterAll, jest } from '@jest/globals';
import { Dialog, Notify } from 'quasar';
import { initializeGlobals } from "src/initialize";

export function setupComponentTest(): void {
  installQuasarPlugin({ plugins: { Dialog, Notify } });
  initializeGlobals();
  window.scrollTo = jest.fn();

  afterAll(() => {
    jest.clearAllMocks();
  });
}
