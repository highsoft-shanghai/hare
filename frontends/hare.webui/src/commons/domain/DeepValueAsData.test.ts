import {describe, expect, it} from '@jest/globals';
import {deepValueAsData} from 'commons/domain/DeepValueAsData';

describe('deepValueAsData', () => {
  it('should never perform any transformation when input is not instance of value', () => {
    expect(deepValueAsData(undefined)).toBeUndefined();
    expect(deepValueAsData('hello')).toBe('hello');
    expect(deepValueAsData(3)).toBe(3);
    expect(deepValueAsData({name: 'john'})).toEqual({name: 'john'});
  });
});
