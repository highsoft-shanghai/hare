import {describe, expect, it} from '@jest/globals';
import {Payload} from './Payload';
import {array} from './ArrayType';

describe('ArrayType ', () => {
  it('should be able to convert inputs into arrays', () => {
    const type = array();
    expect(type.convert('scores', [5, 8])).toStrictEqual([new Payload('scores[0]', 5), new Payload('scores[1]', 8)]);
  });

  it('should be failed to convert non array inputs', () => {
    const type = array();
    expect(() => type.convert('scores', 'non array')).toThrow(`type mismatch: require 'array' but got 'string', in path 'scores'`);
  });
});
