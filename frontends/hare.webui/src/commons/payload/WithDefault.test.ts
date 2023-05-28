import {describe, expect, it} from '@jest/globals'
import {StringType} from './StringType';
import {initializePayloadFactories} from './FactoriesInitializer';

initializePayloadFactories();

describe('WithDefault', () => {
  it('should delegate operations to underlying type', () => {
    const type = (new StringType()).withDefault('default string');
    expect(type.convert('person.name', 'John')).toBe('John');
  });

  it('should return default values when given inputs are optional', () => {
    const type = (new StringType()).withDefault('default string');
    expect(type.convert('person.name', undefined)).toBe('default string');
    expect(type.convert('person.name', null)).toBe('default string');
  });
});
