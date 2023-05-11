import { describe, expect, it } from "@jest/globals";
import { PageKey } from "commons/page/PageKey";

describe('PageKey', () => {
  it('should be able to hold key of pages', () => {
    const key = new PageKey('router.home');
    expect(key.code).toBe('router.home');
  });
});
