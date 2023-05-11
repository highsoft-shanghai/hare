import { I18nMessage } from "commons/i18n/i18n";

export class PageKey extends I18nMessage {
  public get title(): string {
    return this.toString();
  }
}
