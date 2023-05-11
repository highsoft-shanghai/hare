import { PageKey } from "commons/page/PageKey";

export class Page {
  public readonly key: PageKey;

  public constructor(key: PageKey) {
    this.key = key;
  }
}
