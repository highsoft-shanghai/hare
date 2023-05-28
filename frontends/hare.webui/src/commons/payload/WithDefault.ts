import {Type} from './Type';

export class WithDefault<T> extends Type<T> {
  private readonly _type: Type<T>;
  private readonly _defaultValue: T;

  public constructor(type: Type<T>, defaultValue: T) {
    super();
    this._type = type;
    this._defaultValue = defaultValue;
  }

  public convert(path: string, value: unknown): T {
    if (this.isOptional(value)) return this._defaultValue;
    return this._type.convert(path, value);
  }
}
