import { describe, expect, it, jest } from '@jest/globals';
import globals from 'boot/globals';
import { mock } from 'jest-mock-extended';
import { BootFileParams } from '@quasar/app-vite';
import { initializeGlobals } from 'src/initialize';

jest.mock('src/initialize');

describe('boot.globals', () => {
  it('should initialize globals', () => {
    const params = mock<BootFileParams<unknown>>();
    globals(params);
    expect(initializeGlobals).toBeCalled();
  });
});
