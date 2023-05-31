import {describe, expect, it} from '@jest/globals';
import {deepValueAsData} from 'commons/domain/DeepValueAsData';
import {Value} from 'commons/domain/Value';

class TestValue extends Value {
  public asData(): unknown {
    return 'test-value';
  }

  public setValueFromData(data: unknown): void {
    // Do nothing
  }
}

describe('deepValueAsData', () => {
  it('should never perform any transformation when input is not instance of value', () => {
    expect(deepValueAsData(undefined)).toBeUndefined();
    expect(deepValueAsData('hello')).toBe('hello');
    expect(deepValueAsData(3)).toBe(3);
    expect(deepValueAsData({name: 'john'})).toEqual({name: 'john'});
  });

  it('should perform "asData" transformation when input is instance of value', () => {
    expect(deepValueAsData(new TestValue())).toBe('test-value');
  });

  it('should perform "asData" transformation for elements when input is an array', () => {
    expect(deepValueAsData([new TestValue()])).toEqual(['test-value']);
  });
});
