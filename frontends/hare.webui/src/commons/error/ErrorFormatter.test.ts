import {describe, expect, it} from '@jest/globals';
import {ErrorFormatter} from 'commons/error/ErrorFormatter';
import {initializeGlobals} from 'src/initialize';
import {App} from 'vue';
import {mock} from 'jest-mock-extended';

describe('ErrorFormatter', () => {
  initializeGlobals(mock<App>());
  const formatter = new ErrorFormatter();

  it('should be able to format errors', () => {
    expect(formatter.format('string')).toBe('string');
    expect(formatter.format(undefined)).toBe('未知道错误');
  });
});
