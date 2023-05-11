import { describe, expect, it } from "@jest/globals";
import { PageKey } from "commons/page/PageKey";
import { setupComponentTest } from "app/test/utils/component";

setupComponentTest();

describe('PageKey', () => {
  it('should be able to hold key of pages', () => {
    const key = new PageKey('router.home');
    expect(key.code).toBe('router.home');
  });

  it('should be able to resolve page title directly', () => {
    const key = new PageKey('router.home');
    expect(key.toString()).toBe('主页');
  });
});
