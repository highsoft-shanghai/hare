import {Value} from 'commons/domain/Value';

export abstract class SimpleValue<T> extends Value {
  private _value: T;

  protected constructor(value: T) {
    super();
    this._value = value;
  }

  protected get value(): T {
    return this._value;
  }

  protected set value(value: T) {
    this._value = value;
  }
}
