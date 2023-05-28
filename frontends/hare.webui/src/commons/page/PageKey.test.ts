import { describe, expect, it } from '@jest/globals';
import { pagekey, PageKey } from 'commons/page/PageKey';
import { setupComponentTest } from 'app/test/utils/component';

setupComponentTest();

describe('PageKey', () => {
  it('should be able to hold key of pages', () => {
    const key = new PageKey('route.home');
    expect(key.code).toBe('route.home');
  });

  it('should be able to resolve page title directly', () => {
    const key = new PageKey('route.home');
    expect(key.resolvePageTitle()).toBe('首页');
  });

  it('should be able to create by helper function', () => {
    const key = pagekey('route.home');
    expect(key).toBeInstanceOf(PageKey);
    expect(key.code).toBe('route.home');
  });
});
