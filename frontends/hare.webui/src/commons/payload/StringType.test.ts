import {describe, expect, it} from '@jest/globals'
import {StringType} from './StringType';

describe('StringType', () => {
  const type = new StringType();

  it('should be able to convert inputs into strings', () => {
    expect(type.convert('person.name', 'John')).toBe('John');
  });

  it('should failed to convert non string inputs', () => {
    expect(() => type.convert('person.name', 12)).toThrow(`type mismatch: require 'string' but got 'number', in path 'person.name'`);
  });
});
