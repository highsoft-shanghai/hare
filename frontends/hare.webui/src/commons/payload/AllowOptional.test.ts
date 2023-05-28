import {describe, expect, it} from '@jest/globals'
import {StringType} from './StringType';
import {initializePayloadFactories} from './FactoriesInitializer';

initializePayloadFactories();

describe('AllowOptional', () => {
  it('should delegate operations to underlying type', () => {
    const type = (new StringType()).allowOptional();
    expect(type.convert('person.name', 'John')).toBe('John');
  });

  it('should return undefined when given inputs are optional', () => {
    const type = (new StringType()).allowOptional();
    expect(type.convert('person.name', undefined)).toBeUndefined();
    expect(type.convert('person.name', null)).toBeUndefined();
  });
});
