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
    errorHandler('test', 'instance', 'info');
    expect(log).toBeCalledWith('error: ', 'test');
    expect(log).toBeCalledWith('instance: ', 'instance');
    expect(log).toBeCalledWith('info: ', 'info');
  });

  it('should be able to report warnings', () => {
    warningHandler('test', 'instance', 'trace');
    expect(log).toBeCalledWith('message: ', 'test');
    expect(log).toBeCalledWith('instance: ', 'instance');
    expect(log).toBeCalledWith('trace: ', 'trace');
  });
});
