import { PageKey } from "commons/page/PageKey";

export class PageModel {
  public readonly key: PageKey;

  public constructor(key: PageKey) {
    this.key = key;
  }
}
