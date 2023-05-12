import { describe, expect, it } from "@jest/globals";
import { globals } from "commons/global/globals";
import { initializeGlobals } from "src/initialize";
import { App, isProxy } from "vue";
import { mockDeep } from "jest-mock-extended";

describe('globals', () => {
  it('should be able to provide global application instance', () => {
    initializeGlobals(mockDeep<App>() as App<never>);
    expect(globals.application).toBeDefined();
  });

  it('should report error when clients attempt to access undefined application', () => {
    globals.clear();
    expect(() => globals.application).toThrowError('Globals not initialized');
  });

  it('should be reactive', () => {
    expect(isProxy(globals)).toBeTruthy();
  });
});
