import { describe, expect, it } from "@jest/globals";
import { PageHost } from "commons/page/PageHost";
import { Page } from "commons/page/Page";
import { pagekey } from "commons/page/PageKey";

describe('PageHost', () => {
  it('should be able to resolve home-key from home page', () => {
    const host = new PageHost(new Page(pagekey('router.home')));
    expect(host.homeKey).toStrictEqual(pagekey('router.home'));
  });
});
