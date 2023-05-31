import {Type} from './Type';

export class Payload {
  public static readonly EMPTY = new Payload('', undefined);
  public readonly key: string;
  private readonly _value: unknown;

  public constructor(key: string, value: unknown) {
    this.key = key;
    this._value = value;
  }

  public static of(value: unknown): Payload {
    return new Payload('', value);
  }

  public as<T>(type: Type<T>): T {
    return type.convert(this.key, this._value);
  }

  public get(key: string): Payload {
    if (typeof this._value !== 'object') throw new Error(`type mismatch: require 'object' but got '${typeof this._value}', in path '${this.key + '/' + key}'`);
    return new Payload(this.key + '/' + key, (this._value as Record<string, unknown>)[key]);
  }

  public get value(): unknown {
    return this._value;
  }

  public get empty(): boolean {
    return !this.key && !this._value;
  }
}

export function payload(value: unknown): Payload {
  return Payload.of(value);
}
