import {i18n} from 'commons/i18n/i18n';
import {AxiosError} from 'axios';
import {payload} from 'commons/payload/Payload';
import {string} from 'commons/payload/StringType';

export class ErrorFormatter {
  public format(error: unknown): string {
    if (error instanceof AxiosError) return this.formatAxiosError(error);
    if (error instanceof Error) return error.message;
    return error ? error.toString() : i18n('error.unknown').toString();
  }

  private formatAxiosError(error: AxiosError<unknown>): string {
    if (!error.response) return i18n('error.network-error').toString();
    switch (error.response?.status) {
      case 400: return payload(error.response.data).get('message').as(string().withDefault('')) || i18n('error.bad-request').toString();
      case 404: return payload(error.response.data).get('message').as(string().withDefault('')) || i18n('error.not-found').toString();
      default: return i18n('error.server-error').toString();
    }
  }
}
