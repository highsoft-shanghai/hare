import {describe, expect, it} from '@jest/globals';
import {object, ObjectType} from './ObjectType';

describe('ObjectType', () => {
  const type = object();

  it('should be able to convert inputs into objects', () => {
    expect(type.convert('person', {name: 'John'})).toEqual({name: 'John'});
  });

  it('should failed to convert non object inputs', () => {
    expect(() => type.convert('person', 12)).toThrow(`type mismatch: require 'object' but got 'number', in path 'person'`);
  });
});
