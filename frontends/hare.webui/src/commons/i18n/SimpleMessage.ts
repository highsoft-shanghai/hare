import {Message} from 'commons/i18n/Message';

export class SimpleMessage implements Message {
  private readonly value: string;

  public constructor(value: string) {
    this.value = value;
  }

  public toString(): string {
    return this.value;
  }
}
