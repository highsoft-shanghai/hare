import {I18nMessage} from 'commons/i18n/i18nMessage';
import {Message} from 'commons/i18n/Message';

export function i18n(code: string, ...args: unknown[]): Message {
  return new I18nMessage(code, args);
}

export function i18ns(code: string, ...args: unknown[]): string {
  return new I18nMessage(code, args).toString();
}
