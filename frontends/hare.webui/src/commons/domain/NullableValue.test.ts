import {describe, expect, it} from '@jest/globals';
import {NullableValue} from 'commons/domain/NullableValue';

class TestValue extends NullableValue<unknown> {
  public fromData(): void {
  }

  public get underlyingValue(): unknown {
    return this.value;
  }
}

describe('NullableValue', () => {
  it('should be able to serialize as json', () => {
    expect(JSON.stringify(new TestValue('john'))).toBe('"john"');
    expect(JSON.stringify(new TestValue(35))).toBe('35');
    expect(JSON.stringify(new TestValue(['a', 3]))).toBe('["a",3]');
  });

  it('should be able to provide underlying value', () => {
    expect(new TestValue('hello').underlyingValue).toBe('hello');
  })
});
