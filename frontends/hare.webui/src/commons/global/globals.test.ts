import { describe, expect, it } from "@jest/globals";
import { globals } from "commons/global/globals";
import { initializeGlobals } from "src/initialize";

describe('globals', () => {
  it('should be able to provide global application instance', () => {
    initializeGlobals();
    expect(globals.application).toBeDefined();
  });

  it('should be clearable', () => {
    globals.clear();
    expect(globals.application).toBeUndefined();
  });
});
