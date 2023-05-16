import {describe, expect, it} from '@jest/globals';
import {globals, resettableGlobals} from 'commons/global/globals';
import {initializeGlobals} from 'src/initialize';
import {App, isProxy} from 'vue';
import {mock, mockDeep} from 'jest-mock-extended';
import {Router} from 'vue-router';

describe('globals', () => {
  it('should be able to provide global application instance', () => {
    initializeGlobals(mockDeep<App>() as App<never>);
    expect(globals.application).toBeDefined();
  });

  it('should report error when clients attempt to access uninitialized application', () => {
    globals.clear();
    expect(() => globals.application).toThrowError('Global application not initialized');
  });

  it('should be reactive', () => {
    expect(isProxy(globals)).toBeTruthy();
  });

  it('should be able to provide global router instance', () => {
    resettableGlobals.resetRouter(mock<Router>());
    expect(globals.navigator).toBeDefined();
  });

  it('should report error when clients attempt to access uninitialized router', () => {
    globals.clear();
    expect(() => globals.navigator).toThrowError('Global router not initialized');
  });
});
