import { PageModel } from "commons/page/PageModel";
import { PageKey } from "commons/page/PageKey";

export class PageHost {
  public readonly pages: PageModel[] = [];

  public constructor(home: PageModel) {
    this.pages.push(home);
  }

  public get homeKey(): PageKey {
    return this.pages[0].key;
  }
}
