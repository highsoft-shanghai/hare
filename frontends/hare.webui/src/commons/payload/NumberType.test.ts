import {describe, expect, it} from '@jest/globals'
import {NumberType} from './NumberType';

describe('NumberType', () => {
  const type = new NumberType();

  it('should be able to convert inputs into numbers', () => {
    expect(type.convert('person.id', 12)).toBe(12);
  });

  it('should failed to convert from non number inputs', () => {
    expect(() => type.convert('person.id', 'bad')).toThrow(`type mismatch: require 'number' but got 'string', in path 'person.id'`);
  });
});
