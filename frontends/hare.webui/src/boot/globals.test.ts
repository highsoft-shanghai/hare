import { describe, expect, it } from '@jest/globals';
import globals from 'boot/globals';
import { mockDeep } from 'jest-mock-extended';
import { BootFileParams } from '@quasar/app-vite';
import { VueCulture } from 'commons/i18n/VueCulture';

describe('i18n', () => {
  const params = mockDeep<BootFileParams<unknown>>();

  it('should setup global translator correctly', () => {
    globals(params as BootFileParams<never>);
    expect(VueCulture.instance).toBeDefined();
    expect(params.app.use).toBeCalledWith(VueCulture.instance);
  });
});
