import { Page } from "commons/page/Page";
import { PageKey } from "commons/page/PageKey";

export class PageHost {
  public readonly pages: Page[] = [];

  public constructor(home: Page) {
    this.pages.push(home);
  }

  public get homeKey(): PageKey {
    return this.pages[0].key;
  }
}
