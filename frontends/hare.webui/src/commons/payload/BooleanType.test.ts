import {describe, expect, it} from '@jest/globals';
import {boolean, BooleanType} from './BooleanType';

describe('BooleanType', () => {
  const type = boolean();

  it('should be able to convert inputs into booleans', () => {
    expect(type.convert('person.active', true)).toBe(true);
  });

  it('should failed to convert non boolean inputs', () => {
    expect(() => type.convert('person.active', 12)).toThrow(`type mismatch: require 'boolean' but got 'number', in path 'person.active'`);
  });
});
