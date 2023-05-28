import {allowOptional, withDefault} from './Factories';

export abstract class Type<T> {
  public abstract convert(path: string, value: unknown): T;

  public withDefault(defaultValue: T): Type<T> {
    return withDefault(this, defaultValue);
  }

  public allowOptional(): Type<T | undefined> {
    return allowOptional(this);
  }

  protected isOptional(value: unknown): boolean {
    return value === null || value === undefined;
  }
}
