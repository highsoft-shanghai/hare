import { describe, expect, it } from "@jest/globals";
import { PageHost } from "commons/page/PageHost";
import { PageModel } from "commons/page/PageModel";
import { pagekey } from "commons/page/PageKey";

describe('PageHost', () => {
  it('should be able to resolve home-key from home page', () => {
    const host = new PageHost(new PageModel(pagekey('route.home')));
    expect(host.homeKey).toStrictEqual(pagekey('route.home'));
  });

  it('should be able to provide open pages', () => {
    const home = new PageModel(pagekey('route.home'));
    const host = new PageHost(home);
    expect(host.pages).toStrictEqual([home]);
  });
});
