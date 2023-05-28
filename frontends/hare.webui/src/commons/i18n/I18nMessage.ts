import {culture} from 'commons/i18n/CultureAccessor';
import {Message} from 'commons/i18n/Message';

export class I18nMessage implements Message {
  public readonly code: string;
  private readonly args: unknown[];

  public constructor(code: string, ...args: unknown[]) {
    this.code = code;
    this.args = args;
  }

  public toString(): string {
    return culture.localize(this.code, this.args);
  }
}
