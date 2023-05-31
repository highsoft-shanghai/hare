import {Value} from 'commons/domain/Value';

export abstract class NullableValue<T> extends Value {
  private _value: T | null;

  public constructor(value: T | null = null) {
    super();
    this._value = value;
  }

  protected get value(): T | null {
    return this._value;
  }

  protected set value(value: T | null) {
    this._value = value;
  }
}
