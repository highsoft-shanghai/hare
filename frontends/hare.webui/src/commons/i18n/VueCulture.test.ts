import {beforeEach, describe, expect, it, jest} from '@jest/globals';
import {createI18n, I18n} from 'vue-i18n';
import {mockDeep} from 'jest-mock-extended';
import {VueCulture} from 'commons/i18n/VueCulture';
import {MockInstance} from 'jest-mock';

jest.mock('vue-i18n');

describe('VueCulture', () => {
  const mockCreateI18n = createI18n as jest.MockedFunction<typeof createI18n>;
  const mockI18n = mockDeep<I18n<unknown, unknown, unknown, unknown, false>>();

  beforeEach(() => {
    mockCreateI18n.mockReturnValue(mockI18n);
  });

  it('should be able to localize messages by underlying vue-i18n', () => {
    const culture = new VueCulture();
    (mockI18n.global.t as unknown as MockInstance).mockReturnValue('translated-message');
    expect(culture.localize('message.test', 'abc')).toBe('translated-message');
  });
});
