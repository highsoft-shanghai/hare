import { VueCulture } from 'commons/i18n/VueCulture';

export class I18nMessage {
  public readonly code: string;
  private readonly args: unknown[];

  public constructor(code: string, ...args: unknown[]) {
    this.code = code;
    this.args = args;
  }

  public toString(): string {
    return VueCulture.translate(this.code, this.args);
  }
}

export function i18n(code: string, ...args: unknown[]): I18nMessage {
  return new I18nMessage(code, args);
}
