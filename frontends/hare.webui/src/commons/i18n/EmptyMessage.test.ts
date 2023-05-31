import {describe, expect, it} from '@jest/globals';
import {EmptyMessage} from 'commons/i18n/EmptyMessage';

describe('EmptyMessage', () => {
  it('should be able to represent undefined message', () => {
    expect(new EmptyMessage().toString()).toBe(undefined);
    expect(EmptyMessage.INSTANCE.toString()).toBe(undefined);
  });
});
