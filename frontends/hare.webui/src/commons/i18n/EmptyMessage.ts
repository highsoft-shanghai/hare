import {Message} from 'commons/i18n/Message';

export class EmptyMessage implements Message {
  public static readonly INSTANCE = new EmptyMessage();

  public toString(): string | undefined {
    return undefined;
  }
}
