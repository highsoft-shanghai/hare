import {I18nMessage} from 'commons/i18n/I18nMessage';

export class PageKey extends I18nMessage {
  public resolvePageTitle(): string {
    return this.toString();
  }
}

export const pagekey = (code: string) => {
  return new PageKey(code);
}
