import { PageKey } from "commons/page/PageKey";
import { I18nMessage } from "commons/i18n/i18n";

export class PageModel {
  public readonly key: PageKey;

  public constructor(key: PageKey) {
    this.key = key;
  }

  public get title(): I18nMessage {
    return this.key;
  }
}
