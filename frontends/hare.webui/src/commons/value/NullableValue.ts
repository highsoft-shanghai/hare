import {Value} from 'commons/value/Value';

export abstract class NullableValue<T> extends Value {
  private _value: T | null;

  public constructor(value: T | null = null) {
    super();
    this._value = value;
  }

  public get present(): boolean {
    return this._value !== null;
  }

  public toJSON(): unknown {
    return this._value;
  }

  public get value(): T | null {
    return this._value;
  }

  protected changeValue(value: T | null = null) {
    this._value = value;
  }
}
