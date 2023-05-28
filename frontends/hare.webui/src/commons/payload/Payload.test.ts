import {describe, expect, it} from '@jest/globals'
import {Payload} from './Payload';
import {string} from './StringType';
import {number} from './NumberType';

describe('Payload', () => {
  it('should be able to hold values', () => {
    const payload = new Payload('', 'test');
    expect(payload.as(string())).toBe('test');
  });

  it('should be able to construct from values only', () => {
    const payload = Payload.of(12);
    expect(payload.as(number())).toBe(12);
  });

  it('should be able to hold objects', () => {
    const payload = new Payload('person', {id: 123, name: 'John'});
    expect(payload.get('name').as(string())).toBe('John');
  });

  it('should be able to present empty values', () => {
    const payload = Payload.EMPTY;
    expect(payload.empty).toBeTruthy();
  });

  it('should be failed to get values from non-objects', () => {
    expect(() => new Payload('person', undefined).get('key')).toThrow(`type mismatch: require 'object' but got 'undefined', in path 'person'`)
    expect(() => new Payload('person', 12).get('key')).toThrow(`type mismatch: require 'object' but got 'number', in path 'person'`)
    expect(() => new Payload('person', 'john').get('key')).toThrow(`type mismatch: require 'object' but got 'string', in path 'person'`)
    expect(() => new Payload('person', null).get('key')).toThrow(`null`)
  });
});
