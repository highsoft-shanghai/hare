import {beforeEach, describe, expect, it, jest} from '@jest/globals';
import {errorHandler, warningHandler} from 'commons/error/error-handler';
import {setupComponentTest} from 'app/test/utils/component';

setupComponentTest();

describe('error-handler', () => {
  let log: unknown;

  beforeEach(() => {
    log = jest.spyOn(global.console, 'log');
  });

  it('should be able to report errors', () => {
    errorHandler('test');
    expect(log).toBeCalledWith('test', undefined, undefined);
  });

  it('should be able to report warnings', () => {
    warningHandler('test', undefined, 'trace');
    expect(log).toBeCalledWith('test', undefined, 'trace');
  });
});
