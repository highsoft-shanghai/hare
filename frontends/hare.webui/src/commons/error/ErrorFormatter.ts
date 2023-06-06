import {i18n} from 'commons/i18n/i18n';
import {AxiosError} from 'axios';
import {payload} from 'commons/payload/Payload';
import {string} from 'commons/payload/StringType';
import {Message} from 'commons/i18n/Message';

export class ErrorFormatter {
  public format(error: unknown): string {
    if (error instanceof AxiosError) return this.formatAxiosError(error);
    if (error instanceof Error) return error.message;
    return error ? error.toString() : this.localize(i18n('error.unknown'));
  }

  private formatAxiosError(error: AxiosError<unknown>): string {
    if (!error.response) return this.localize(i18n('error.network-error'));
    switch (error.response?.status) {
      case 400:return payload(error.response.data).get('message').as(string().withDefault('')) || this.localize(i18n('error.bad-request'));
      case 404:return payload(error.response.data).get('message').as(string().withDefault('')) || this.localize(i18n('error.not-found'));
      default:return this.localize(i18n('error.server-error'));
    }
  }

  private localize(message: Message): string {
    return message.toString() || '';
  }
}
