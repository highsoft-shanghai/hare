import {Type} from './Type';

export class AllowOptional<T> extends Type<T | undefined> {
  private readonly _type: Type<T>;

  public constructor(type: Type<T>) {
    super();
    this._type = type;
  }

  public convert(path: string, value: unknown): T | undefined {
    return this.isOptional(value) ? undefined : this._type.convert(path, value);
  }
}
