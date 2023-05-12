import { describe, expect, it } from "@jest/globals";
import { globals } from "commons/global/globals";
import { initializeGlobals } from "src/initialize";
import { isProxy } from "vue";
import app from "src/App.vue";

describe('globals', () => {
  it('should be able to provide global application instance', () => {
    initializeGlobals(app);
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
