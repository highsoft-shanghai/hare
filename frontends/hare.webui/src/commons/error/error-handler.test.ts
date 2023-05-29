import {beforeEach, describe, expect, it, jest} from '@jest/globals';
import {errorHandler} from 'commons/error/error-handler';
import {setupComponentTest} from 'app/test/utils/component';

setupComponentTest();

describe('error-handler', () => {
  let log: unknown;

  beforeEach(() => {
    log = jest.spyOn(global.console, 'log');
  });

  it('should be able to report string errors', () => {
    errorHandler('test');
    expect(log).toBeCalledWith('test', undefined, undefined);
  });
});
