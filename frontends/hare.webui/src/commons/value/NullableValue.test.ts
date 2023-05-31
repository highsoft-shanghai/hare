import {describe, expect, it} from '@jest/globals';
import {NullableValue} from 'commons/value/NullableValue';

class TestValue extends NullableValue<unknown> {
  public assign(data: unknown): void {
    this.changeValue(data);
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

  it('should default to null', () => {
    expect(new TestValue().underlyingValue).toBeNull();
    expect(new TestValue(undefined).underlyingValue).toBeNull();
  });

  it('should change value to null if input is undefined', () => {
    const value = new TestValue('present');
    value.assign(undefined);
    expect(value.underlyingValue).toBeNull();
    expect(value.toJSON()).toBeNull();
  });
});
