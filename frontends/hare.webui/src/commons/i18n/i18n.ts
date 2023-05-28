import {I18nMessage} from 'commons/i18n/I18nMessage';
import {Message} from 'commons/i18n/Message';

export function i18n(code: string, ...args: unknown[]): Message {
  return new I18nMessage(code, args);
}
