import {i18n} from 'commons/i18n/i18n';

export class ErrorFormatter {
  public format(error: unknown): string {
    if (error instanceof Error) return error.message;
    return error ? error.toString() : i18n('error.unknown').toString();
  }
}
