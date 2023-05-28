import {I18nMessage} from 'commons/i18n/I18nMessage';
import {Message} from 'commons/i18n/Message';
import {SimpleMessage} from 'commons/i18n/SimpleMessage';

export function i18n(code: string, ...args: unknown[]): Message {
  return new I18nMessage(code, args);
}

export function message(value: string): Message {
  return new SimpleMessage(value);
}
