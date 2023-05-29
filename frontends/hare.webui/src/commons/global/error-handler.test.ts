import {describe, expect, it, jest} from '@jest/globals';
import {errorHandler} from 'commons/global/error-handler';

describe('error-handler', () => {
  it('should be able to report string errors', () => {
    const log = jest.spyOn(global.console, 'log');
    errorHandler('test');
    expect(log).toBeCalledWith('test', undefined, undefined);
  });
});
