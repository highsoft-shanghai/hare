import {describe, expect, it} from '@jest/globals';
import i18n from 'boot/i18n';
import {mockDeep} from 'jest-mock-extended';
import {BootFileParams} from '@quasar/app-vite';
import {Translator} from 'commons/i18n/Translator';

describe('i18n', () => {
  const params = mockDeep<BootFileParams<unknown>>();
  it('should setup global translator correctly', () => {
    i18n(params as BootFileParams<never>);
    expect(Translator.instance).toBeDefined();
    expect(params.app.use).toBeCalledWith(Translator.instance);
  });
});